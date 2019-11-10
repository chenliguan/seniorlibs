package com.seniorlibs.binder.aidl;

// 注意：1、尽管Book类和IBookManager位于相同的包中，但是在为IBookManager仍然要导入Book类，这就是AIDL的特殊之处
import com.seniorlibs.binder.aidl.Book;
import com.seniorlibs.binder.aidl.IOnNewBookArrivedListener;

interface IBookManager {

     List<Book> getBookList();

     // in、out或者inout,in表示输入型参数，out表示输出型参数，inout表示输入输出型参数
     void addBook(in Book book);

     void registerListener(IOnNewBookArrivedListener listener);

     void unregisterListener(IOnNewBookArrivedListener listener);
}