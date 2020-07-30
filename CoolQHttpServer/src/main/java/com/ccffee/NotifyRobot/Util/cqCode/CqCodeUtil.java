package com.ccffee.NotifyRobot.Util.cqCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CqCodeUtil {
    public static CqCode getCqCodeByCqCOdeStr(String cqCodeStr){
        CqCode cqCode = new CqCode();

        String cqCodeStrArr[] = cqCodeStr.split(",");

        String cq = cqCodeStrArr[0].substring(4);

        switch (cq){
            case "image":
                cqCode.setCQ(Cq.IMAGE);
                break;
            case "at":
                cqCode.setCQ(Cq.AT);
                break;
            default:
                return null;
        }

        HashMap<String, String> param = new HashMap<>();

        for (int i = 1; i < cqCodeStrArr.length; i++){
            String cqCodeSubString = cqCodeStrArr[i];
            if (i == cqCodeStrArr.length-1){
                cqCodeSubString = cqCodeSubString.substring(0, cqCodeSubString.length()-1);
            }
            String cqCodeSubStringSplitArr[] = cqCodeSubString.split("=");
            String key = cqCodeSubStringSplitArr[0];
            StringBuffer value = new StringBuffer();
            value.append(cqCodeSubStringSplitArr[1]);
            for (int j = 2; j < cqCodeSubStringSplitArr.length; j++)
                value.append("="+cqCodeSubStringSplitArr[j]);
            param.put(key, value.toString());
        }

        cqCode.setParam(param);

        return cqCode;
    }

    public static String getCqCodeStrByCqCode(CqCode cqCode){
        StringBuilder cqCodeStr = new StringBuilder();

        cqCodeStr.append("[");

        cqCodeStr.append("CQ:" + cqCode.getCQ().getCq());

        HashMap<String, String> param = cqCode.getParam();

        for (String key: param.keySet()){
            cqCodeStr.append(",");
            cqCodeStr.append(key + "=" + param.get(key));
        }

        cqCodeStr.append("]");

        return cqCodeStr.toString();
    }

    public static List<String> cqCodeStrFilter(String message){
        List<String> codeStrList = new ArrayList();
        while (message.indexOf("[CQ") != -1){
            message = message.substring(message.indexOf("[CQ"));

            codeStrList.add(message.substring(0, message.indexOf("]")+1));

            message = message.substring(message.indexOf("]")+1);
        }

        return codeStrList;
    }
}
