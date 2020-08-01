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

    public static List<CqCode> cqCodeFilter(String message){
        List<CqCode> codeStrList = new ArrayList();
        while (message.indexOf("[CQ") != -1){
            message = message.substring(message.indexOf("[CQ"));

            CqCode cqCode = getCqCodeByCqCOdeStr(message.substring(0, message.indexOf("]")+1));

            if (cqCode != null)
                codeStrList.add(cqCode);

            message = message.substring(message.indexOf("]")+1);
        }

        return codeStrList;
    }

    /**
     * 判断消息是否@了指定qq号，qq号为"*"是代表任何人（即有无AT消息）
     * @param message 消息
     * @param qqNum qq号
     * @return
     */
    public static Boolean checkMessageIsAT2QQNum(String message, String qqNum){
        List<CqCode> cqCodeList = cqCodeFilter(message);

        for (CqCode cqCode: cqCodeList){
            if (cqCode == null) continue;
            if (cqCode.getCQ() == Cq.AT){
                if (qqNum.equals("*") || cqCode.getParam().get("qq").equals(qqNum)){
                    return true;
                }
            }
        }
        return false;
    }

    public static List<CqCode> getATCqCodeByMessage(String message){
        List<CqCode> aTCqCodeList = new ArrayList<>();
        List<CqCode> cqCodeList = cqCodeFilter(message);

        for (CqCode cqCode: cqCodeList){
            if (cqCode.getCQ() == Cq.AT)
                aTCqCodeList.add(cqCode);
        }

        return aTCqCodeList;

    }

    public static List<CqCode> getIMAGECqCodeByMessage(String message){
        List<CqCode> CqCodeList = new ArrayList<>();
        List<CqCode> cqCodeList = cqCodeFilter(message);

        for (CqCode cqCode: cqCodeList){
            if (cqCode.getCQ() == Cq.IMAGE)
                CqCodeList.add(cqCode);
        }

        return CqCodeList;

    }
}
