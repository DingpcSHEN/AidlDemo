package com.shen.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.aidl.fuck.IFuckInterface;

/**
 * 服务端：提供服务，对应需要使用该服务的对象或者进程叫做客户端
 * 如果是当前进程访问，那么使用的是简单的代理模式：直接获取当前进程中的ibinder对象
 * 如果被其他进程访问，那么使用的是binder通信方式：客户端通过IFuckInterface.Stub.asInterface方式转换成IFuckInterface.Stub.Proxy
 *                                                 之后IFuckInterface.Stub.Proxy通过mRemote.transact方式以binder驱动程序向服务端进程发送命令
 */
public class FuckService extends Service {
    private String mName = null;
    private IBinder ibinder = new IFuckInterface.Stub() {
        @Override
        public void setName(String name) throws RemoteException {
            mName = name;
        }
        @Override
        public String getName() throws RemoteException {
            return mName;
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }
}
