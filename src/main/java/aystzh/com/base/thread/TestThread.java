package aystzh.com.base.thread;

import aystzh.com.study.entity.db.AuthorEntity;

/**
 * Created by zhanghuan on 2020/3/1.
 */
public class TestThread extends BaseTaskEventThread<AuthorEntity> {

    public TestThread(String name) {
        super(name);
    }

    @Override
    public short getWorkers() {
        return 4;
    }

    @Override
    public void doWork(AuthorEntity authorEntity) throws Exception {
        for (int i = 0; i < 10000; i++) {
            System.out.println("启动线程...");
        }
    }
}
