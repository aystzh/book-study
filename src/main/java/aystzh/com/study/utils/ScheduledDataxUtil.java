package aystzh.com.study.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 lz
 * @创建时间 2020/2/14
 * @描述
 */
public class ScheduledDataxUtil {
    public static Long oneDay = (long) 24 * 60 * 60 * 1000;        //每一天批量执行一次
    public static Long initDelay = (long) 0 * 1000;            //延迟0分钟开始执行
    public static String jsonPath = "";//json文件夹地址
    public static String dataxPath = "";    //datax的python文件地址

    //执行datax
    public static void exeDatax() {
        try {
            System.out.println("------------------start----------------------");
            String[] str = getFileName(jsonPath);
            for (String name : str) {
                String windowcmd = "python " + dataxPath + " " + jsonPath + "/" + name;
                System.out.println(windowcmd);
                Process pr = Runtime.getRuntime().exec(windowcmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                pr.waitFor();
            }
            System.out.println("----------------end------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取文件夹下所有 json 文件名
    public static String[] getFileName(String path) {
        File file = new File(path);
        String[] fileName = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".json")) {
                    return true;
                }
                return false;
            }
        });
        return fileName;
    }

    public static void main(String[] args) {
        //定时任务
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                exeDatax();
            }

        }, initDelay, oneDay, TimeUnit.MILLISECONDS);

    }
}


