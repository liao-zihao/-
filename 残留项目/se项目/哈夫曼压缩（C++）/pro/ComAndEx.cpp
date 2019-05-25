#include"ComAndEx.h"
void ComAndEx::Compress()
{
	string outfilename=ScanCharacter(); //ɨ��Դ�ļ����ַ������༰����������Դ�ļ����ļ�������string���ͱ�������
	if (outfilename == "NULL") return;
	CreateHuffmanTree(); //�����������
	CreateHuffmanCode(); //���ɹ���������
	cout << "Input the name of Compressed file :";
	string filename;
	cin >> filename;
	ofstream out(filename, ios::binary);
	if (!out)
	{
		cout << "File open failed.." << endl;
		return;
	}
	out.write((char*)&H_number, sizeof(int));
	for (int i = 1; i <= H_number; ++i)//���������ַ����ַ���Ƶ��
	{
		out.write((char*)&(HuffmanTree[i].name), sizeof(char));
		out.write((char*)&(HuffmanTree[i].weight), sizeof(int));
	}
	ifstream in(outfilename, ios::binary);//�ڶ���ɨ��Դ�ļ�
	if (!in)
	{
		cout << "File open failed" << endl<<endl;
		return;
	}
	unsigned char char_temp = '\0';//��8λ���޷��ű�����Ϊ��д�ַ��ĵ�Ԫ
	string unit;//�洢���������룬����ĸ������ڵ��ڰ�λ�ͺ�char_temp����|����
	while (true)
	{
		char_temp=in.get();//��ԭ�ļ��ж�ȡһ���ַ�
		if (in.eof()) break;//�ж��Ƿ񵽴��ļ���ĩβ
		for (int i = 1; i <= H_number; ++i)//�ڴ�ȡ������������������ҵ����ַ���Ӧ�ı��벢������洢��unit��
		{
			if (Huffmancode[i].name == char_temp)
			{
				for (auto elem : Huffmancode[i].code)//��unit���������
				{
					if (elem == 1)
						unit.push_back('1');
					else
						unit.push_back('0');
				}
				break;
			}
		}
		while (unit.length() >= 8)//���unit�д洢�Ĺ����������λ�����ڵ���8���ͽ�ǰ��λͨ��λ����д��char_temp��
		{
			char_temp = '\0';
			for (int i = 0; i < 8; ++i)
			{
				char_temp =char_temp<< 1;
				if (unit[i] == '1')
					char_temp = char_temp | 1;
			}
			auto p = unit.begin();
			unit.erase(p, p + 8);//���unit�е�ǰ��λ����
			out.write((char*)&char_temp, sizeof(unsigned char));//�����й�����������Ϣ���ַ�д�뵽ѹ���ļ���ȥ
		}
	}
	if (unit.length() > 0)//����������λ�Ĺ���������
	{
		char_temp = '\0';
		for (auto i = 0; i < unit.length(); ++i)
		{
			char_temp = char_temp << 1;
			if (unit[i] == '1')
				char_temp = char_temp | 1;
		}
		for (auto i = 0; i < (8 - unit.length()); ++i)//�������Ƶ�����λ
			char_temp = char_temp << 1;
		out.write((char*)&char_temp, sizeof(unsigned char));
	}
	in.close();
	out.close();
	//ѹ���ļ���ɣ�����������������ʹ洢�����������������ڴ��ͷŵ��������ַ�����������Ϊ0
	HuffmanTree.resize(0);
	Huffmancode.resize(0);
	H_number = 0;
}

//���ɹ���������ĺ���
void ComAndEx::CreateHuffmanCode()
{
	Huffmancode_node elem;
	Huffmancode.push_back(elem);
	if (H_number == 1)
	{
		Huffmancode.push_back(elem);
		Huffmancode[1].name =HuffmanTree[1].name;
		Huffmancode[1].code.push_back(1);
	}
	for (int i = 1; i <= H_number; ++i)
	{
		Huffmancode.push_back(elem);
		Huffmancode[i].name = HuffmanTree[i].name;
		int temp_p = HuffmanTree[i].p;
		int temp_nodeindex = HuffmanTree[i].Hnodeindex;
		auto p = Huffmancode[i].code.end();
		while (temp_p != 0)
		{
			if (HuffmanTree[temp_p].lchild == temp_nodeindex)
			{
				p=Huffmancode[i].code.insert(p, 0);
			}
			else
			{
				p=Huffmancode[i].code.insert(p, 1);
			}
			temp_nodeindex = HuffmanTree[temp_p].Hnodeindex;
			temp_p = HuffmanTree[temp_p].p;
		}
	}
}

//������������ĺ���
void ComAndEx::CreateHuffmanTree()
{
	if (H_number == 1)
	{
		HuffmanTree[1].lchild = HuffmanTree[1].rchild = 1;
	}
	int mark = H_number + 1;
	int x = 0, y = 0;
	int min = INT_MAX, secmin = 0;
	while (mark < 2 * H_number)
	{
		for (int j = 1; j < mark; ++j)
		{
			if (HuffmanTree[j].p == 0 && HuffmanTree[j].weight<min)
			{
				y = x;
				secmin = min;
				min = HuffmanTree[j].weight;
				x = HuffmanTree[j].Hnodeindex;
			}
			else if (HuffmanTree[j].p == 0 && HuffmanTree[j].weight < secmin)
			{
				secmin = HuffmanTree[j].weight;
				y = HuffmanTree[j].Hnodeindex;
			}
			else;
		}
		HuffmanTree[mark].weight = HuffmanTree[x].weight + HuffmanTree[y].weight;
		HuffmanTree[mark].lchild = x;
		HuffmanTree[mark].rchild = y;
		HuffmanTree[x].p = mark;
		HuffmanTree[y].p = mark;
		mark++;
		x = y = 0;
		min = INT_MAX, secmin = 0;
	}
	HuffmanTree[2 * H_number - 1].p = 0;
}

//ɨ��ԭ�ļ����ַ������༰Ƶ�ȵĺ���
string ComAndEx::ScanCharacter()
{
	cout << "Input the Text file :";
	string filename;
	cin >> filename;
	ifstream in(filename,ios::binary);//�Զ����Ƶĸ�ʽ��Դ�ļ�
	if (!in)                          //���ָ����Դ�ļ��Ƿ����
	{
		cout << "File not found.." << endl<<endl;
		return "NULL";
	}
	unsigned char temp = '\0';  //��һ��8λ���޷��ű��������ζ�ȡԴ�ļ�����Ϣ
	Hnode *temparry = new Hnode[256];  //����һ����ʱ���飬����ͳ���ַ������༰Ƶ��
	while (true)           //�ж��Ƿ񵽴��ļ���ĩβ
	{
		temp = in.get();
		if (in.eof())break;
		temparry[temp].name = temp;
		temparry[temp].weight++;
	}
	in.close();  //�ر�in��Դ�ļ��Ĺ���
	Hnode elem;
	HuffmanTree.push_back(elem);//ʹ�ù�������Ч�Ĵ洢�Ǵ�HuffmanTree[1]��ʼ
	H_number = 0;
	for (int i = 0; i < 256; i++)//ѡ���Ƶ�ȴ���0���ַ��洢��������������
	{
		if (temparry[i].weight != 0)
		{
			H_number++;//ͳ���ַ���������
			HuffmanTree.push_back(elem);
			*(HuffmanTree.end()-1) = temparry[i];
		}
	}
	if (HuffmanTree.size()== 1)
	{
		cout << "The text is empty.." << endl << endl;
		return "NULL";
	}
	delete[]temparry;//ͳ������Ժ��ͷ���ʱ������ڴ�
	for (int i = 1; i <= (H_number - 1); i++)//ΪҶ�ڵ��˫�׽ڵ㿪���ڴ�
	{
		HuffmanTree.push_back(elem);
	}
	for (int i = 0; i < 2 * H_number; i++)//Ϊ���������������Ԫ�ؽ�������
	{
		HuffmanTree[i].Hnodeindex = i;
	}
	return filename;
}
void ComAndEx::Extract()
{
	cout << "Input the Compressed file:";
	string filename;
	cin >> filename;
	ifstream in(filename, ios::binary);
	if (!in)
	{
		cout << "File open failed.." << endl;
		return;
	}
	in.read((char*)&H_number,sizeof(int));//��ȡ�ַ�������
	Hnode elem;
	HuffmanTree.push_back(elem);
	for (int i = 1; i <= H_number; ++i)//��ȡ�ַ������༰Ƶ��
	{
		HuffmanTree.push_back(elem);
		in.read((char*)&(HuffmanTree[i].name), sizeof(char));
		in.read((char*)&(HuffmanTree[i].weight), sizeof(int));
	}
	for(int i=1;i<=H_number-1;++i)//���������Ĺ���������
		HuffmanTree.push_back(elem);
	for (int i = 0; i < 2 * H_number; i++)//Ϊ����������Ԫ�ؽ�������
	{
		HuffmanTree[i].Hnodeindex = i;
	}
	CreateHuffmanTree();//���ɹ�������
	unsigned  char temp_char = '\0';//������ȡ����������ĵ�λ
	unsigned long long length = 0;
	for (int i = 1; i <= H_number; ++i)//����ԭ�ļ����ַ��ĸ���
		length = length + HuffmanTree[i].weight;
	int top = HuffmanTree[2 * H_number - 1].Hnodeindex;//top�洢���ǽ�����������ʼ��ʱ���ڵ������
	cout << "Input the file name after compressed:";
	string extractedname;
	cin >> extractedname;
	ofstream out(extractedname,ios::binary);
	if (!out)
	{
		cout << "File open failed.." << endl<<endl;
		return;
	}
    while(length)
	{
		temp_char = in.get();//
		for (int i = 0; i<8;++i)
		{
			if (temp_char & 128)//������λΪ1
				top = HuffmanTree[top].rchild;//top�洢�����Һ��ӵ�����
			else
				top = HuffmanTree[top].lchild;//������λΪ0��top�洢�������ӵ�����
			if (top <=H_number)//����Ӹ��ڵ㵽��Ҷ�ڵ�
			{
				out <<HuffmanTree[top].name;//����ַ�
				length--;//����ԭ�ļ���δ������ַ��ĸ���
				if (length ==0 )break;//���ԭ�ļ���δ����ĸ���Ϊ0������ԭ�ļ����ַ��Ѿ�ȫ�������ѭ������Ҫ�ټ���
				top = HuffmanTree[2 * H_number - 1].Hnodeindex;//����top��λΪ���ڵ������
			}
			temp_char = temp_char << 1;//���û����Ҷ�ڵ㣬��ôtemp_char����һλ������ѭ��
		}
	}
	in.close();
	out.close();
	//ѹ���ļ���ɣ�����������������ʹ洢�����������������ڴ��ͷŵ��������ַ�����������Ϊ0
	HuffmanTree.resize(0);
	Huffmancode.resize(0);
	H_number = 0;
}
