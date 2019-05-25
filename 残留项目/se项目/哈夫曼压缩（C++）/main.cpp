/*
	文件ComAndEx.h包含一个类，这个类表示整个执行过程，包含计算字符对应数量，建树，产生哈夫曼编码，压缩，解压缩
	Hnode.h表示树的一个节点，包含字符和对应的数量
	Huffmancode.h表示字符和其对应的哈夫曼编码
	ComAndEx.cpp是ComAndEx.h的具体实现
*/
#include"ComAndEx.h"
int main()
{
	ComAndEx *comandex = new ComAndEx;
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
			comandex->Compress();
		}
		else if (select == 2)
		{
			comandex->Extract();
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
