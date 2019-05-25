#include <iostream>
#include<string>
#include<vector>
#include<fstream>
using namespace std;
class code_node   //�洢�ַ��Ĺ���������
{
public:
	unsigned char name;  //�ַ�������
	vector<int> code;    //��vector�����洢����������
	code_node()
	{
		name = '\0';
	}
};
class HTreenode  //���������Ĵ洢�洢�ṹ
{
public:
	unsigned char name;    //8λ�Ĵ洢��Ԫ���洢�ַ���256�֣�
	unsigned  weight;  //�洢�ַ���Ƶ��
	int p; //˫�׽ڵ�,����ָ
	int lchild; //����
	int rchild; //�Һ���
	int HTreenodeIndex;  //�ڵ�����������δʹ��ָ�룬ʹ��һ��������ʾ�����ڵ�ı�ʶ
	HTreenode() //��ʼ�����ݳ�Ա
	{
		name = '\0';
		weight = 0;
		p = 0;
		HTreenodeIndex = 0;
		lchild = rchild = 0;
	}
};
class Execute   //ѹ����ѹ��
{
public:
	void Compress(); //ѹ���ļ��ĺ���
	void Extract();  //��ѹ�ļ��ĺ���
	string Scan();//ɨ��Դ�ļ����ַ������༰����
	void CreateTree();//������������
	void GetCode();//���ɹ���������
protected:
	//vector�����������鵫�ǿ������Ĳ���
	vector<HTreenode> HuffmanTree;  //�洢��������������
	vector<code_node>Hcode;  //�洢���������������
	int Char_number; //�ַ���������
};
//ɨ��ԭ�ļ����ַ������༰Ƶ�ȵĺ���
string Execute::Scan()
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
	HTreenode *temparry = new HTreenode[256];  //����һ����ʱ���飬����ͳ���ַ������༰Ƶ��
	while (true)           //�ж��Ƿ񵽴��ļ���ĩβ
	{
		temp = in.get();
		if (in.eof())break;
		temparry[temp].name = temp;
		temparry[temp].weight++;
	}
	in.close();  //�ر�in��Դ�ļ��Ĺ���
	HTreenode element;
	HuffmanTree.push_back(element);//ʹ�ù�������Ч�Ĵ洢�Ǵ�HuffmanTree[1]��ʼ������������
	Char_number = 0;
	for (int i = 0; i < 256; i++)//ѡ���Ƶ�ȴ���0���ַ��洢��������������
	{
		if (temparry[i].weight != 0)
		{
			Char_number++;//ͳ���ַ���������
			HuffmanTree.push_back(element);
			*(HuffmanTree.end()-1) = temparry[i];
		}
	}
	if (HuffmanTree.size()== 1)
	{
		cout << "The text is empty.." << endl << endl;
		return "NULL";
	}
	delete[]temparry;//ͳ������Ժ��ͷ���ʱ������ڴ�
	for (int i = 1; i <= (Char_number - 1); i++)//ΪҶ�ڵ��˫�׽ڵ㿪���ڴ�
	{
		HuffmanTree.push_back(element);
	}
	for (int i = 0; i < 2 * Char_number; i++)//Ϊ���������������Ԫ�ؽ�������
	{
		HuffmanTree[i].HTreenodeIndex = i;
	}
	//����HuffmanTree����ĵ�һ�����ã���������Ч�ڵ㣬Ȼ����Ϊ����׼���Ŀսڵ�
	return filename;
}

void Execute::Compress()//ѹ������ʵ��
{
	string outfilename=Scan(); //ɨ��Դ�ļ����ַ������༰��������ɨ��ɹ��򷵻�Դ�ļ����ļ�������string���ͱ������棬���ɹ�����"NULL";
	if (outfilename == "NULL") return;
	CreateTree(); //�����������
	GetCode(); //���ɹ���������
	cout << "Input the name of Compressed file :";
	string filename;
	cin >> filename;
	ofstream out(filename, ios::binary);
	if (!out)
	{
		cout << "File open failed.." << endl<<endl;
		return;
	}
	out.write((char*)&Char_number, sizeof(int));//��ѹ���ļ�ͷ��д���й���Ϣ���˴�Ϊ��ͬ�ַ��ĸ���
	for (int i = 1; i <= Char_number; ++i)//���������ַ����ַ���Ƶ��
	{
		out.write((char*)&(HuffmanTree[i].name), sizeof(char));//����д������ַ�����Ƶ��
		out.write((char*)&(HuffmanTree[i].weight), sizeof(int));
	}
	ifstream in(outfilename, ios::binary);//�ڶ���ɨ��Դ�ļ�
	if (!in)
	{
		cout << "File open failed" << endl<<endl;
		return;
	}
	unsigned char char_temp = '\0';//��8λ���޷��ű�����Ϊ��д�ַ��ĵ�Ԫ
	string single_byte;//�洢���������룬����ĸ������ڵ��ڰ�λ�ͺ�char_temp���л����
	while (true)
	{
		char_temp=in.get();//��ԭ�ļ���ÿ�ζ�ȡһ���ַ�
		if (in.eof()) break;//�ж��Ƿ񵽴��ļ���ĩβ
		for (int i = 1; i <= Char_number; ++i)//�ڴ�ȡ������������������ҵ����ַ���Ӧ�ı��벢������洢��single_byte��
		{
			if (Hcode[i].name == char_temp)
			{
				for (auto element : Hcode[i].code)//��single_byte���������
				{
					if (element == 1)
						single_byte.push_back('1');
					else
						single_byte.push_back('0');
				}
				break;
			}
		}
		while (single_byte.length() >= 8)//���single_byte�д洢�Ĺ����������λ�����ڵ���8���ͽ�ǰ��λͨ��λ����д��char_temp��
		{
			char_temp = '\0';
			for (int i = 0; i < 8; ++i)
			{
				char_temp =char_temp<< 1;//���Ʋ���
				if (single_byte[i] == '1')//��1��д1������1Ĭ��д0
					char_temp = char_temp | 1;
			}
			auto p = single_byte.begin();
			single_byte.erase(p, p + 8);//���single_byte�е�ǰ��λ����
			out.write((char*)&char_temp, sizeof(unsigned char));//�����й�����������Ϣ���ַ�д�뵽ѹ���ļ���ȥ
		}
	}
	if (single_byte.length() > 0)//����������λ�Ĺ���������
	{
		char_temp = '\0';
		for (auto i = 0; i < single_byte.length(); ++i)
		{
			char_temp = char_temp << 1;
			if (single_byte[i] == '1')
				char_temp = char_temp | 1;
		}
		for (auto i = 0; i < (8 - single_byte.length()); ++i)//�������Ƶ�����λ
			char_temp = char_temp << 1;//�����λĬ����0
		out.write((char*)&char_temp, sizeof(unsigned char));
	}
	in.close();
	out.close();
	//ѹ���ļ���ɣ�����������������ʹ洢�����������������ڴ��ͷŵ��������ַ�����������Ϊ0
	HuffmanTree.resize(0);
	Hcode.resize(0);
	Char_number = 0;
	cout<<"Compressed successfully!"<<endl<<endl;
}

//���ɹ���������ĺ���
void Execute::GetCode()
{
	code_node element;
	Hcode.push_back(element);//��ǰ����һ��Ҳ��Ϊ�˷������
	if (Char_number == 1)//���ֻ��һ���ַ���ֱ�Ӹ�1
	{
		Hcode.push_back(element);
		Hcode[1].name =HuffmanTree[1].name;
		Hcode[1].code.push_back(1);
	}
	for (int i = 1; i <= Char_number; ++i)
	{
		Hcode.push_back(element);//������Ԫ���ٽ���Ԫ�ظ�ֵ
		Hcode[i].name = HuffmanTree[i].name;
		int temp_p = HuffmanTree[i].p;
		int temp_nodeindex = HuffmanTree[i].HTreenodeIndex;
		auto p = Hcode[i].code.end();//��p�Ƶ�code�����λ����ʵ���ǵ�һλ
		while (temp_p != 0)//������롣�����ĵײ��õ�����
		{
			if (HuffmanTree[temp_p].lchild == temp_nodeindex)//����и��ڵ����Լ�Ϊ���ӣ�����ͷ����0
			{
				p=Hcode[i].code.insert(p, 0);
			}
			else
			{
				p=Hcode[i].code.insert(p, 1);//�и��ڵ����Լ�Ϊ���ӣ�����ͷ����1
			}
			temp_nodeindex = HuffmanTree[temp_p].HTreenodeIndex;
			temp_p = HuffmanTree[temp_p].p;//�����������ڵ�
		}
	}
}

//������������ĺ���
void Execute::CreateTree()//����
/*
	����ʵ���Ͼ���һ�����飨��Ϊ��ָ�������Σ�
	ÿ��Ԫ�ش���һ���ڵ�
	��1��Char_number���Ѿ��ó���ÿ���ַ�����Ľڵ�
	��Char_number��2 * Char_number-1����δ���ӵ���ڵ㣨��Ϊ����n���ַ�����󽨳ɵ�������2n-1���ڵ�,���������⣩
	��mark������ڵ��������Ҳ����˵��λ�ã�,markԽС��Խ������������һ��mark�����������ĸ�
	ʵ����ֻ�ǰ�ָ�뻻�ɿ�Ѱ�ҵ����֣�mark��
*/
{
	if (Char_number == 1)//ֻ��һ���ַ������������ֱ�Ӵ���
	{
		HuffmanTree[1].lchild = HuffmanTree[1].rchild = 1;
	}
	//���ڶ���ַ�
	int mark = Char_number + 1;
	int x = 0, y = 0;//x��y�ֱ��ʾ������СƵ��
	int min = INT_MAX, secmin = 0;//��ʼ������Ƶ����С��ֵ
	while (mark < 2 * Char_number)
	{
		for (int j = 1; j < mark; ++j)
		{
			if (HuffmanTree[j].p == 0 && HuffmanTree[j].weight<min)//���ڵ���0����δ�������У�ѡ��Ƶ����С���滻��ǰmin
			{
				y = x;
				secmin = min;
				min = HuffmanTree[j].weight;
				x = HuffmanTree[j].HTreenodeIndex;
			}
			else if (HuffmanTree[j].p == 0 && HuffmanTree[j].weight < secmin)//���ڵ���0����δ�������У�ѡ���ڶ�С�滻secmin
			{
				secmin = HuffmanTree[j].weight;
				y = HuffmanTree[j].HTreenodeIndex;
			}
			else;
		}
		HuffmanTree[mark].weight = HuffmanTree[x].weight + HuffmanTree[y].weight;//��������С��Ƶ�������������ڵ㣬���ڵ�ı����mark��ֵ
		HuffmanTree[mark].lchild = x;//�ֱ�ָ���ӽڵ�
		HuffmanTree[mark].rchild = y;
		HuffmanTree[x].p = mark;//�ֱ�ָ�򸸽ڵ�
		HuffmanTree[y].p = mark;
		mark++;
		x = y = 0;
		min = INT_MAX, secmin = 0;
	}
	HuffmanTree[2 * Char_number - 1].p = 0;//���һ���ڵ㸸ֵ��Ϊ0
}
void Execute::Extract()//��ѹ����ʵ��
{
	cout << "Input the Compressed file:";
	string filename;
	cin >> filename;
	ifstream in(filename, ios::binary);
	if (!in)
	{
		cout << "File open failed.." << endl<<endl;
		return;
	}
	in.read((char*)&Char_number,sizeof(int));//��ȡ�ַ�������
	HTreenode element;
	HuffmanTree.push_back(element);
	for (int i = 1; i <= Char_number; ++i)//��ȡ�ַ������༰Ƶ��
	{
		HuffmanTree.push_back(element);
		in.read((char*)&(HuffmanTree[i].name), sizeof(char));
		in.read((char*)&(HuffmanTree[i].weight), sizeof(int));
	}
	for(int i=1;i<=Char_number-1;++i)//���������Ĺ���������
		HuffmanTree.push_back(element);
	for (int i = 0; i < 2 * Char_number; i++)//Ϊ����������Ԫ�ؽ�������
	{
		HuffmanTree[i].HTreenodeIndex = i;
	}
	CreateTree();//���ɹ�������
	unsigned  char temp_char = '\0';//������ȡ����������ĵ�λ
	unsigned long long length = 0;
	for (int i = 1; i <= Char_number; ++i)//����ԭ�ļ����ַ��ĸ���
		length = length + HuffmanTree[i].weight;
	int top = HuffmanTree[2 * Char_number - 1].HTreenodeIndex;//top�洢���ǽ�����������ʼ��ʱ���ڵ������
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
			if (top <=Char_number)//����Ӹ��ڵ㵽��Ҷ�ڵ�
			{
				out <<HuffmanTree[top].name;//����ַ�
				length--;//����ԭ�ļ���δ������ַ��ĸ���
				if (length ==0 )break;//���ԭ�ļ���δ����ĸ���Ϊ0������ԭ�ļ����ַ��Ѿ�ȫ�������ѭ������Ҫ�ټ���
				top = HuffmanTree[2 * Char_number - 1].HTreenodeIndex;//����top��λΪ���ڵ������
			}
			temp_char = temp_char << 1;//���û����Ҷ�ڵ㣬��ôtemp_char����һλ������ѭ��
		}
	}
	in.close();
	out.close();
	//ѹ���ļ���ɣ�����������������ʹ洢�����������������ڴ��ͷŵ��������ַ�����������Ϊ0
	HuffmanTree.resize(0);
	Hcode.resize(0);
	Char_number = 0;
	cout<<"Extracted successfully!"<<endl<<endl;
}
int main()
{
    Execute *execute = new Execute;
	while (1)
	{
		cout << "1 " << "Compress file" << endl;
		cout << "2 " << "Extract file" << endl;
		cout << "3 " << "Exit" << endl;
		cout << "Please choose :";
		int select = 0;
		cin >> select;
		if (select == 1)
		{
			execute->Compress();
		}
		else if (select == 2)
		{
			execute->Extract();
		}
		else if (select == 3)
		{
			break;
		}
		else
		{
			cout << "Please input correct number.." << endl;
		}
	}
	return 0;
}
