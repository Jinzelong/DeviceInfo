import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InputStream2String {
    public String filenameTemp;
    public String modelName;
    public StringBuffer stringBuffer = new StringBuffer();


    //使用List进行查看
    public ArrayList<String> demo(InputStream inputStream){
        ArrayList<String> result = new ArrayList<String> ();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result.add(temp) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    //获取制造商
    public String InputStream2String_Manu(InputStream inputStream) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelName = result + " ";
        // Convert.method2(filenameTemp,result);
        stringBuffer.append("设备制造商："+result+"\n");
        return result;
    }

    //型号名
    public String InputStream2String_ModelName(InputStream inputStream) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  Convert.method2(filenameTemp,result);
        stringBuffer.append("设备名称："+result);
        modelName = modelName + result +" ";
        return result;
    }


    //创建文件夹
    public boolean creatTxtFile() throws IOException {
        File directory = new File("");
        String filepath = directory.getAbsolutePath()+"/";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        boolean flag = false;
        filenameTemp = filepath + modelName+ df.format(new Date()) + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }

        return flag;
    }
    //安卓设备列表
    public boolean InputStream2String_List(InputStream inputStream)  {
        String neirong = "";
        boolean result = true; //默认已连入设备
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                neirong += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = neirong.length();
       // System.out.println("设备信息数据大小："+neirong.length());
        //neirong 大小List of devices attached = 26
        //小于等于26说明没有设备连入
        if (length <= 26) {
            result = false;
        }
        //stringBuilder.append("\n"+result);
        return result;//如果未连接，则显示false
    }

    //直接输出
    public String InputStream2String_Default(String name,InputStream inputStream) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // Convert.method2(filenameTemp,result);
        stringBuffer.append("\n"+name+":"+result);
        return result;
    }



    //CPU品牌
    public String CpuBrand(String name){
        switch (name){
            case "Qualcomm" :
                return "高通";
            case "MTK":
                return "联发科";
            case "SAMSUNG":
                return "三星";
            case"Hisilicon":
                return "海思麒麟";
            default:
                return "获取失败或者为新添加型号处理器";
        }
    }

    //CPU型号
    public String InputStream2String_CPU_Brand(InputStream inputStream) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] name = result.split("Hardware\t:");
        String name_sub =  name[name.length-1].substring(1);
        String name_sub_detail[] = name_sub.split("\\s+");
        String name_Chinese =CpuBrand(name_sub_detail[0]) ;
        //Convert.method2(filenameTemp,name_Chinese+" " + name_sub);
        stringBuffer.append("\n"+"CPU品牌:"+name_Chinese+" "+name_sub);
        return name_Chinese+" "+name_sub;
    }
    //CPU最大主频

    //CPU核心数
    public int InputStream2String_CPU_Core_Number(InputStream inputStream) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] number = result.split("processor\t:");
        return number.length-1;
    }
    //CPU架构

    //CPU架构（详细）

    //内存信息
    public int InputStream2String_RAM(InputStream inputStream) throws IOException{
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String RAM = result.substring(17,result.indexOf("k"));
        int RAM_num = Integer.parseInt(RAM.substring(0,RAM.indexOf(" "))) ;

        int RAM_integer = 0;
        if (RAM_num>1*1024*1024 && RAM_num<2*1024*1024){
            RAM_integer = 2;
        }
        //3G内存
        if (RAM_num>2*1024*1024 && RAM_num<3*1024*1024){
            RAM_integer =  3;
        }
        //4G内存
        if (RAM_num>3*1024*1024 && RAM_num<4*1024*1024){
            RAM_integer =  4;
        }
        //6G内存
        if (RAM_num>5*1024*1024 && RAM_num<6*1024*1024){
            RAM_integer =  6;
        }
        //8G内存
        if (RAM_num>7*1024*1024 && RAM_num<8*1024*1024){
            RAM_integer =  8;
        }
        //10G内存
        if (RAM_num>9*1024*1024 && RAM_num<10*1024*1024){
            RAM_integer =  10;
        }
        //12G内存
        if (RAM_num>11*1024*1024 && RAM_num<12*1024*1024){
            RAM_integer =  12;
        }
        //System.out.println(RAM_num);
        //Convert.method2(filenameTemp,RAM_integer+"G");
        stringBuffer.append("\n"+"内存大小："+RAM_integer);
        return RAM_integer;
    }
    //屏幕分辨率

    //屏幕尺寸

    //是否为全面屏

    //屏幕占比

    //屏幕显示密度

    //存储大小，使用VmallocTotal:   262143936 kB
    //和内存大小相似 8G 16G 32G 64G 128G 256G 512G 1T
    public int InputStream2String_ROM(InputStream inputStream) throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ROM[] = result.split("VmallocTotal:   ");
        // System.out.println(ROM[1]);
        int ROM_num = Integer.parseInt(ROM[1].substring(0, ROM[1].indexOf(" ")));

        return ROM_num;
}

    //CPU

    //分档

    //top

    //设备状态

    //借用人

    //是否接入wanmei Office

    //设备类型

    //海外版设备是否接入Google Play

    public double CPU_Core_Speed(InputStream inputStream) throws IOException{
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        double speed = 0.00;
        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.isEmpty()){
            speed =  0;
        }else {
            speed = Double.parseDouble(result);
        }
        speed = speed / 1000000;
        BigDecimal b = new BigDecimal(speed);
        speed = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        speed = (double) Math.round(speed * 100) / 100;
        stringBuffer.append("\n"+speed);
        return speed;
    }
    public void printString(){
        Convert.method2(filenameTemp,stringBuffer);
        System.out.println(stringBuffer);
    }

//处理器核数返回中文
    public String num_to_Chinese(int number){
        switch (number){
            case 1:
                return "单核";
            case 2:
               // Convert.method2(filenameTemp,"双核");
                stringBuffer.append("\n"+"双核");
                return "双核";
            case 3:
                return "三核";
            case 4:
                //Convert.method2(filenameTemp,"四核");
                stringBuffer.append("\n"+"四核");
                return "四核";
            case 5:
                return "五核";
            case 6:
                //Convert.method2(filenameTemp,"六核");
                stringBuffer.append("\n"+"六核");
                return "六核";
            case 7:
                return "七核";
            case 8:
               // Convert.method2(filenameTemp,"八核");
                stringBuffer.append("\n"+"八核");
                return "八核";
            case 9:
                return "九核";
            case 10:
                //Convert.method2(filenameTemp,"十核");
                stringBuffer.append("\n"+"十核");
                return "十核";
            default:
                return "获取失败";
        }

    }


}