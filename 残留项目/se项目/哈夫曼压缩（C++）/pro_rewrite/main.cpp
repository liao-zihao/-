#include <iostream>
#include<string>
#include<vector>
#include<fstream>
using namespace std;
class code_node   //存储字符的哈夫曼编码
{
public:
	unsigned char name;  //字符的名称
	vector<int> code;    //用vector容器存储哈夫曼编码
	code_node()
	{
		name = '\0';
	}
};
class HTreenode  //哈夫曼树的存储存储结构
{
public:
	unsigned char name;    //8位的存储单元来存储字符（256种）
	unsigned  weight;  //存储字符的频度
	int p; //双亲节点,向上指
	int lchild; //左孩子
	int rchild; //右孩子
	int HTreenodeIndex;  //节点索引，由于未使用指针，使用一个整数表示各个节点的标识
	HTreenode() //初始化数据成员
	{
		name = '\0';
		weight = 0;
		p = 0;
		HTreenodeIndex = 0;
		lchild = rchild = 0;
	}
};
class Execute   //压缩解压类
{
public:
	void Compress(); //压缩文件的函数
	void Extract();  //解压文件的函数
	string Scan();//扫描源文件种字符的种类及个数
	void CreateTree();//建立哈夫曼树
	void GetCode();//生成哈夫曼编码
protected:
	//vector就类似于数组但是可以灵活的操作
	vector<HTreenode> HuffmanTree;  //存储哈夫曼树的数组
	vector<code_node>Hcode;  //存储哈夫曼编码的数组
	int Char_number; //字符的种类数
};
//扫描原文件中字符的种类及频度的函数
string Execute::Scan()
{
	cout << "Input the Text file :";
	string filename;
	cin >> filename;
	ifstream in(filename,ios::binary);//以二进制的格式打开源文件
	if (!in)                          //检查指定的源文件是否存在
	{
		cout << "File not found.." << endl<<endl;
		return "NULL";
	}
	unsigned char temp = '\0';  //用一个8位的无符号变量来依次读取源文件的信息
	HTreenode *temparry = new HTreenode[256];  //创建一个临时数组，用于统计字符的种类及频度
	while (true)           //判断是否到达文件的末尾
	{
		temp = in.get();
		if (in.eof())break;
		temparry[temp].name = temp;
		temparry[temp].weight++;
	}
	in.close();  //关闭in与源文件的关联
	HTreenode element;
	HuffmanTree.push_back(element);//使得哈夫曼有效的存储是从HuffmanTree[1]开始，便于做索引
	Char_number = 0;
	for (int i = 0; i < 256; i++)//选择出频度大于0的字符存储到哈夫曼数组中
	{
		if (temparry[i].weight != 0)
		{
			Char_number++;//统计字符的种类数
			HuffmanTree.push_back(element);
			*(HuffmanTree.end()-1) = temparry[i];
		}
	}
	if (HuffmanTree.size()== 1)
	{
		cout << "The text is empty.." << endl << endl;
		return "NULL";
	}
	delete[]temparry;//统计完毕以后释放临时数组的内存
	for (int i = 1; i <= (Char_number - 1); i++)//为叶节点的双亲节点开辟内存
	{
		HuffmanTree.push_back(element);
	}
	for (int i = 0; i < 2 * Char_number; i++)//为哈夫曼数组的所有元素建立索引
	{
		HuffmanTree[i].HTreenodeIndex = i;
	}
	//至此HuffmanTree数组的第一个无用，后面是有效节点，然后是为建树准备的空节点
	return filename;
}

void Execute::Compress()//压缩函数实现
{
	string outfilename=Scan(); //扫描源文件种字符的种类及个数，若扫描成功则返回源文件的文件名并用string类型变量保存，不成功则是"NULL";
	if (outfilename == "NULL") return;
	CreateTree(); //构造哈夫曼树
	GetCode(); //生成哈夫曼编码
	cout << "Input the name of Compressed file :";
	string filename;
	cin >> filename;
	ofstream out(filename, ios::binary);
	if (!out)
	{
		cout << "File open failed.." << endl<<endl;
		return;
	}
	out.write((char*)&Char_number, sizeof(int));//向压缩文件头部写入有关信息，此处为不同字符的个数
	for (int i = 1; i <= Char_number; ++i)//依次输入字符及字符的频度
	{
		out.write((char*)&(HuffmanTree[i].name), sizeof(char));//依次写入各个字符及其频数
		out.write((char*)&(HuffmanTree[i].weight), sizeof(int));
	}
	ifstream in(outfilename, ios::binary);//第二遍扫描源文件
	if (!in)
	{
		cout << "File open failed" << endl<<endl;
		return;
	}
	unsigned char char_temp = '\0';//用8位的无符号变量作为读写字符的单元
	string single_byte;//存储哈夫曼编码，编码的个数大于等于八位就和char_temp进行或操作
	while (true)
	{
		char_temp=in.get();//在原文件中每次读取一个字符
		if (in.eof()) break;//判断是否到达文件的末尾
		for (int i = 1; i <= Char_number; ++i)//在存取哈夫曼编码的数组中找到该字符对应的编码并将编码存储到single_byte中
		{
			if (Hcode[i].name == char_temp)
			{
				for (auto element : Hcode[i].code)//向single_byte中输入编码
				{
					if (element == 1)
						single_byte.push_back('1');
					else
						single_byte.push_back('0');
				}
				break;
			}
		}
		while (single_byte.length() >= 8)//如果single_byte中存储的哈夫曼编码的位数大于等于8，就将前八位通过位操作写入char_temp中
		{
			char_temp = '\0';
			for (int i = 0; i < 8; ++i)
			{
				char_temp =char_temp<< 1;//左移操作
				if (single_byte[i] == '1')//是1就写1，不是1默认写0
					char_temp = char_temp | 1;
			}
			auto p = single_byte.begin();
			single_byte.erase(p, p + 8);//清空single_byte中的前八位编码
			out.write((char*)&char_temp, sizeof(unsigned char));//将载有哈夫曼编码信息的字符写入到压缩文件中去
		}
	}
	if (single_byte.length() > 0)//处理最后不足八位的哈夫曼编码
	{
		char_temp = '\0';
		for (auto i = 0; i < single_byte.length(); ++i)
		{
			char_temp = char_temp << 1;
			if (single_byte[i] == '1')
				char_temp = char_temp | 1;
		}
		for (auto i = 0; i < (8 - single_byte.length()); ++i)//将编码移到到高位
			char_temp = char_temp << 1;//不足八位默认添0
		out.write((char*)&char_temp, sizeof(unsigned char));
	}
	in.close();
	out.close();
	//压缩文件完成，将哈夫曼树的数组和存储哈夫曼编码的数组的内存释放掉，并将字符的种类数置为0
	HuffmanTree.resize(0);
	Hcode.resize(0);
	Char_number = 0;
	cout<<"Compressed successfully!"<<endl<<endl;
}

//生成哈夫曼编码的函数
void Execute::GetCode()
{
	code_node element;
	Hcode.push_back(element);//提前填入一个也是为了方便起见
	if (Char_number == 1)//如果只有一个字符就直接赋1
	{
		Hcode.push_back(element);
		Hcode[1].name =HuffmanTree[1].name;
		Hcode[1].code.push_back(1);
	}
	for (int i = 1; i <= Char_number; ++i)
	{
		Hcode.push_back(element);//先填入元素再进行元素赋值
		Hcode[i].name = HuffmanTree[i].name;
		int temp_p = HuffmanTree[i].p;
		int temp_nodeindex = HuffmanTree[i].HTreenodeIndex;
		auto p = Hcode[i].code.end();//把p移到code的最后位，其实就是第一位
		while (temp_p != 0)//反向插入。从树的底部得到编码
		{
			if (HuffmanTree[temp_p].lchild == temp_nodeindex)//如果有父节点且自己为左子，则在头部添0
			{
				p=Hcode[i].code.insert(p, 0);
			}
			else
			{
				p=Hcode[i].code.insert(p, 1);//有父节点且自己为右子，则在头部添1
			}
			temp_nodeindex = HuffmanTree[temp_p].HTreenodeIndex;
			temp_p = HuffmanTree[temp_p].p;//往上索引父节点
		}
	}
}

//构造哈夫曼树的函数
void Execute::CreateTree()//建树
/*
	此树实际上就是一个数组（因为用指针容易晕）
	每个元素代表一个节点
	从1到Char_number是已经得出的每个字符代表的节点
	从Char_number到2 * Char_number-1代表还未连接的诸父节点（因为若有n个字符，最后建成的树必有2n-1个节点,这个不难理解）
	用mark代表诸父节点的索引（也可以说是位置）,mark越小就越早加入树，最后一个mark就是整棵树的根
	实际上只是把指针换成可寻找的数字（mark）
*/
{
	if (Char_number == 1)//只有一个字符特殊情况可以直接处理
	{
		HuffmanTree[1].lchild = HuffmanTree[1].rchild = 1;
	}
	//对于多个字符
	int mark = Char_number + 1;
	int x = 0, y = 0;//x，y分别表示两个最小频数
	int min = INT_MAX, secmin = 0;//初始化两个频数最小的值
	while (mark < 2 * Char_number)
	{
		for (int j = 1; j < mark; ++j)
		{
			if (HuffmanTree[j].p == 0 && HuffmanTree[j].weight<min)//父节点是0代表还未加入树中，选出频数最小的替换当前min
			{
				y = x;
				secmin = min;
				min = HuffmanTree[j].weight;
				x = HuffmanTree[j].HTreenodeIndex;
			}
			else if (HuffmanTree[j].p == 0 && HuffmanTree[j].weight < secmin)//父节点是0代表还未加入树中，选出第二小替换secmin
			{
				secmin = HuffmanTree[j].weight;
				y = HuffmanTree[j].HTreenodeIndex;
			}
			else;
		}
		HuffmanTree[mark].weight = HuffmanTree[x].weight + HuffmanTree[y].weight;//将两个最小的频数加起来给父节点，父节点的标号是mark的值
		HuffmanTree[mark].lchild = x;//分别指向子节点
		HuffmanTree[mark].rchild = y;
		HuffmanTree[x].p = mark;//分别指向父节点
		HuffmanTree[y].p = mark;
		mark++;
		x = y = 0;
		min = INT_MAX, secmin = 0;
	}
	HuffmanTree[2 * Char_number - 1].p = 0;//最后一个节点父值设为0
}
void Execute::Extract()//解压函数实现
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
	in.read((char*)&Char_number,sizeof(int));//读取字符种类数
	HTreenode element;
	HuffmanTree.push_back(element);
	for (int i = 1; i <= Char_number; ++i)//读取字符的种类及频度
	{
		HuffmanTree.push_back(element);
		in.read((char*)&(HuffmanTree[i].name), sizeof(char));
		in.read((char*)&(HuffmanTree[i].weight), sizeof(int));
	}
	for(int i=1;i<=Char_number-1;++i)//建立完整的哈夫曼数组
		HuffmanTree.push_back(element);
	for (int i = 0; i < 2 * Char_number; i++)//为哈夫曼数组元素建立索引
	{
		HuffmanTree[i].HTreenodeIndex = i;
	}
	CreateTree();//生成哈夫曼树
	unsigned  char temp_char = '\0';//用来读取哈夫曼编码的单位
	unsigned long long length = 0;
	for (int i = 1; i <= Char_number; ++i)//计算原文件的字符的个数
		length = length + HuffmanTree[i].weight;
	int top = HuffmanTree[2 * Char_number - 1].HTreenodeIndex;//top存储的是结点的索引，初始化时根节点的索引
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
			if (temp_char & 128)//如果最高位为1
				top = HuffmanTree[top].rchild;//top存储结点的右孩子的索引
			else
				top = HuffmanTree[top].lchild;//如果最高位为0，top存储结点的左孩子的索引
			if (top <=Char_number)//如果从根节点到达叶节点
			{
				out <<HuffmanTree[top].name;//输出字符
				length--;//更新原文件中未输出的字符的个数
				if (length ==0 )break;//如果原文件中未输出的个数为0，表明原文件的字符已经全部输出，循环不需要再继续
				top = HuffmanTree[2 * Char_number - 1].HTreenodeIndex;//否则top复位为根节点的索引
			}
			temp_char = temp_char << 1;//如果没到达叶节点，那么temp_char左移一位，继续循环
		}
	}
	in.close();
	out.close();
	//压缩文件完成，将哈夫曼树的数组和存储哈夫曼编码的数组的内存释放掉，并将字符的种类数置为0
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
