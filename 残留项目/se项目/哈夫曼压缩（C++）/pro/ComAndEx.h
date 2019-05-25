#ifndef COMANDEX_H
#define COMANDEX_H
#include<vector>
#include"Hnode.h"
#include"Huffmancode_node.h"
#include<iostream>
#include<fstream>
using namespace std;
class ComAndEx   //ѹ����ѹ���ͷ�ļ�
{
public:
	void Compress(); //ѹ���ļ��ĺ���
	void Extract();  //��ѹ�ļ��ĺ���
	string ScanCharacter();//ɨ��Դ�ļ����ַ������༰����
	void CreateHuffmanTree();//������������
	void CreateHuffmanCode();//���ɹ���������
protected:
	vector<Hnode> HuffmanTree;  //�洢��������������
	vector<Huffmancode_node>Huffmancode;  //�洢���������������
	int H_number; //�ַ���������
};

#endif
