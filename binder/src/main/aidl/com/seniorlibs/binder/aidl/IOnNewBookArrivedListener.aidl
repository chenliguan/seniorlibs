package com.seniorlibs.binder.aidl;

import com.seniorlibs.binder.aidl.Book;

interface IOnNewBookArrivedListener {

    void onNewBookArrived(in Book newBook);
}
