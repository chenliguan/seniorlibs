package com.seniorlibs.binder.aidl;

// 注意：1、必须与实体类同名
//       2、如果AIDL文件中用到了自定义的Parcelable对象，那么必须新增一个和他同名的AIDL文件，并在其中声明它为Parcelable类型
//       3、服务端和客户端要保持AIDL包结构一致，客户端需要反序列化服务端中和AIDL接口相关的所有类，如果类的完整路径不一样的话，就无法成功反序列化
parcelable Book;