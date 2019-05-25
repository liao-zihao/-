#include <iostream>

using namespace std;
class b1{
public:
    virtual b1();
    b1(){
        cout<<"b1"<<endl;
    }
//    ~b1(){
//        cout<<"b1d"<<endl;
//    }
//    void display(){
//        cout<<"b1c"<<endl;
//    }
};
class b2{
public:
    b2(){
        cout<<"b2"<<endl;
    }
//    ~b2(){
//        cout<<"b2d"<<endl;
//    }
//    void display(){
//        cout<<"b2c"<<endl;
//    }
};
class d:public b1,public b2{
public:
    d(){
        cout<<"d"<<endl;
    }
//    ~d(){
//        cout<<"dd"<<endl;
//    }
//    void display(){
//        cout<<"dc"<<endl;
//    }
};
int main()
{
    d D;
    b1 *ptr=new d();
//    ptr->display();
    delete ptr;
    return 0;
}
