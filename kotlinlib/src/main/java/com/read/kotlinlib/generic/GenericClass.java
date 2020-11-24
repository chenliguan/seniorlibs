package com.read.kotlinlib.generic;

import java.util.List;
import java.util.Map;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/11/22.
 * Mender:
 * Modify:
 * Description: 泛型类
 */
public abstract class GenericClass<T> {

    Class<T> type;

    public GenericClass() {
        this.type = (Class<T>) getClass();
    }

    public List<Map<T, T>> getValue() {
        return null;
    }



    class InnerNode<T> {
        T node;

        public InnerNode() {
            this.node = null;
        }

        public T getNode() {
            return node;
        }
    }

//    @groovyx.ast.bytecode.Bytecode
//    public Object getNode() {
//        aload 0
//        getfield com.read.kotlinlib.generic.GenericClass$InnerNode.node >> Object
//        areturn
//    }

//    // access flags 0x1
//    // signature ()TT;
//    // declaration: T getNode()
//    public getNode()Ljava/lang/Object;
//     L0
//      LINENUMBER 35 L0
//      ALOAD 0
//      GETFIELD com/read/kotlinlib/generic/GenericClass$InnerNode.node : Ljava/lang/Object;
//      ARETURN
//     L1
//      LOCALVARIABLE this Lcom/read/kotlinlib/generic/GenericClass$InnerNode; L0 L1 0
//      // signature Lcom/read/kotlinlib/generic/GenericClass<TT;>.InnerNode<TT;>;
//      // declaration: com.read.kotlinlib.generic.GenericClass<T>.InnerNode<T>
//      MAXSTACK = 1
//      MAXLOCALS = 1
}
