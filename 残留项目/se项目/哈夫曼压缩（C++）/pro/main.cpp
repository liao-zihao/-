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
