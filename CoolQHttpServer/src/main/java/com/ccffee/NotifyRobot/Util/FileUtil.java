package com.ccffee.NotifyRobot.Util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class FileUtil {

    /**
     * 下载网络图片
     * @param httpUrl 图片地址
     * @param imageName 图片后缀
     * @return 图片完整路径
     */
    public static String downloadImage(String httpUrl, String imagePath, String imageName) throws Exception {
        URL url;
        BufferedInputStream in;
        FileOutputStream file;
        String name = UUID.randomUUID().toString().replace("-","") + "." + imageName;
        String path = imagePath + "/" + name;

        url = new URL(httpUrl);
        in = new BufferedInputStream(url.openStream());
        file = new FileOutputStream(new File(path));
        int t;
        while ((t = in.read()) != -1) {
            file.write(t);
        }
        file.close();
        in.close();

        return name;
    }

}
