import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Convert {
    public static String output_path;
    private static int pause_time;
    public static int CPU_Core_number;
    public static void main(String[] args) {
        //暂停时间
        pause_time = 1000;//每条信息获取时间间隔为1s;

        Scanner input = new Scanner(System.in);
        File directory = new File("");
        System.out.println("插入设备后，输入2,按下回车后，开始获取设备信息");
        while (input.nextInt() != 1) {
            boolean device_online = false;
            String adbHome = directory.getAbsolutePath() + "\\adb.exe";//adb地址
            //String adbHome = "/Users/jinzelong/Library/Android/sdk/platform-tools/adb";
            System.out.println(adbHome);
            //初始化
            String cmd = adbHome + " version";//用于检测adb是否可以正常运行（正常显示版本和地址，异常报错）

            //获取设备列表
            String device_list = adbHome + " devices";//查询已连接设备、模拟器（空、离线、已连接）
            ArrayList<String> device = new ArrayList<String>();



            //wifi
            //String wifi = adbHome +"adb shell netcfg";//查看网络状态
            String Mac_address = adbHome + " shell cat /sys/class/net/wlan0/address";//查看Mac地址（有时候拿不到，要分安卓版本进行测试 ）


            //存储
            String ROM_size = adbHome + " shell cat /proc/meminfo";//查看存储大小

            String GPU = adbHome + " install ";


            //暂时为空
            //------------------------------------------------------------------------------------
            String Full_Screen;//是不是全面屏（）
            String Screen_size = adbHome + "";//获取屏幕尺寸
            String Android_subVersion = adbHome + "";//查询安卓子版本



            Process process;

            try {
//
                InputStream2String inputStream2String = new InputStream2String();
                Thread.sleep(pause_time);
                System.out.println("\n---------ADB版本-------------");
                process = Runtime.getRuntime().exec(cmd);
                System.out.println(InputStream2String(process.getInputStream()));//输出ADB版本

                Thread.sleep(pause_time);
                System.out.println("---------设备列表-------------");
                process = Runtime.getRuntime().exec(device_list);//获取信息前，先确定设备是否在线
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                if (device_online) {
                    process = Runtime.getRuntime().exec(device_list);
                    device = inputStream2String.demo(process.getInputStream());
                    System.out.println(device);

                } else {
                    System.exit(0);
                    System.out.println("设备列表为空，请插入设备并打开开发者模式后重试");
                }

                int device_num = device.size()-2;//设备数
                String device_serial[] = new String[100];

                System.out.println("设备列表：");
                for (int i = 0;i<device_num;i++){
                    device_serial[i] = device.get(i+1).split("\t")[0];
                    System.out.println(i+"."+device_serial[i]);
                }
                System.out.println("请选择要获取的设备信息");
                int device_choose = input.nextInt();
                adbHome = adbHome + " -s "+ device_serial[device_choose];


                //获取设备制造商
                String device_manufacturer = adbHome + " shell getprop ro.product.manufacturer";


                //获取安卓版本
                String Android_version = adbHome + " shell getprop ro.build.version.release";//查询安卓版本

                //CPU
                String Soc_brand = adbHome + " shell getprop ro.product.board";//获取SOC型号（System on Chip）
                String Soc_model = adbHome + " shell getprop ro.product.cpu.abilist";//获取CPUabi
                String CPU_info = adbHome + " shell cat /proc/cpuinfo";//获取CPU详细信息

                ArrayList<String> CPU_Speed = new ArrayList<String> ();

                //内存
                String RAM = adbHome + " shell cat /proc/meminfo";//获取内存大小

                //屏幕
                String Screen_resolution = adbHome + " shell wm size";//查看屏幕分辨率
                String Product_name = adbHome + " shell getprop ro.product.name";//获取设备名称
                String Product_model = adbHome + " shell getprop ro.product.model";//查看设备信息
                String Screen_density = adbHome + " shell wm density";//查看屏幕密度

                Thread.sleep(pause_time);
                System.out.println("---------设备制造商-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(device_manufacturer);
                    System.out.println(inputStream2String.InputStream2String_Manu(process.getInputStream()));
                }

                Thread.sleep(pause_time);
                System.out.println("---------设备型号------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Product_model);
                    //method2(output_path,"------------设备型号------------");
                    System.out.println(inputStream2String.InputStream2String_ModelName(process.getInputStream()));
                }
                inputStream2String.creatTxtFile();
                output_path = inputStream2String.filenameTemp;//输出文件所在路径

                Thread.sleep(pause_time);
                System.out.println("---------安卓版本-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Android_version);
                    //method2(output_path,"---------安卓版本-------------");//写入文件
                    System.out.println(inputStream2String.InputStream2String_Default("安卓版本",process.getInputStream()));
                }



                Thread.sleep(pause_time);
                System.out.println("---------主板/CPU名称-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Soc_brand);
                    //method2(output_path,"---------主板/CPU名称-------------");
                    System.out.println(inputStream2String.InputStream2String_Default("主板/CPU名称",process.getInputStream()));
                }


                Thread.sleep(pause_time);
                System.out.println("---------CPU核心数-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(CPU_info);
                   // method2(output_path,"---------CPU核心数-------------");
                    CPU_Core_number = inputStream2String.InputStream2String_CPU_Core_Number(process.getInputStream());
                    System.out.println(inputStream2String.num_to_Chinese(CPU_Core_number));
                    //现存问题：联发科十核识别为四核
                }

                Thread.sleep(pause_time);
                System.out.println("---------CPU核心情况-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    for (int i = 0;i<CPU_Core_number;i++) {
                        CPU_Speed.add(i,adbHome+" shell cat /sys/devices/system/cpu/cpu"+ i +"/cpufreq/scaling_max_freq");
                    }
                    for (int i = 0,j=0;i<CPU_Core_number;i++){
                        j = i+1;
                        process = Runtime.getRuntime().exec(CPU_Speed.get(i));
                        System.out.println("core"+j + ":"+inputStream2String.CPU_Core_Speed(process.getInputStream())+"GHz");
                    }

                }


                Thread.sleep(pause_time);
                System.out.println();
                System.out.println("---------CPU架构（ARM)-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Soc_model);
                   // method2(output_path,"---------CPU架构（ARM)-------------");
                    System.out.println(inputStream2String.InputStream2String_Default("CPU架构",process.getInputStream()));
                }

                Thread.sleep(pause_time);
                System.out.println("-----------CPU品牌 型号------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(CPU_info);
                   // method2(output_path,"-----------CPU品牌 型号------------");
                    System.out.println(inputStream2String.InputStream2String_CPU_Brand(process.getInputStream()));

                }


                Thread.sleep(pause_time);
                System.out.println("-----------内存信息-----------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(RAM);
                   // method2(output_path,"-----------内存信息-----------");
                    System.out.println(inputStream2String.InputStream2String_RAM(process.getInputStream()) + "G");
                    System.out.println();
                }


                Thread.sleep(pause_time);
                System.out.println("------------屏幕分辨率------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Screen_resolution);
                   // method2(output_path,"------------屏幕分辨率------------");
                    System.out.println(inputStream2String.InputStream2String_Default("屏幕分辩率",process.getInputStream()));
                }

//            System.out.println("------屏幕尺寸---");
//            //process = Runtime.getRuntime().exec(Screen_size);//屏幕尺寸
//            System.out.println();

//            System.out.println("---是否为全面屏--------");
//            //process = Runtime.getRuntime().exec(Full_Screen);//全面屏
//            System.out.println("Yes");
//            System.out.println("");

//            System.out.println("---屏幕占比--------");
//            System.out.println("81.2%");
//            System.out.println();
                Thread.sleep(pause_time);
                System.out.println("-------------屏幕显示密度----------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Screen_density);
                    System.out.println(inputStream2String.InputStream2String_Default("屏幕显示密度",process.getInputStream()));
                }


//            Thread.sleep(2500);
//            System.out.println("-------分档------");
//            System.out.println("高");
//            System.out.println("");
//
//            System.out.println("-------top------");
//            System.out.println("100");
//            System.out.println("");
//
//
//            System.out.println("------设备状态-----");
//            System.out.println("借出/归还");
//            System.out.println("");
//
//
//            System.out.println("---借用人--------");
//            System.out.println("金泽龙");
//            System.out.println("");
//
//
//            System.out.println("------是否接入wanmei Office--------");
//            System.out.println("接入/未接入");
//            System.out.println("");
//
//
//            System.out.println("----设备类型--------");
//            System.out.println("手机");
//            System.out.println("");
//
//
//            System.out.println("----海外版设备是否接入Google Play--------");
//            System.out.println("是");
//            System.out.println("");
//
//            System.out.println("-----设备名--------");
//            System.out.println("小米");
//            System.out.println("");println

                Thread.sleep(pause_time);
                System.out.println("------------设备名称------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Product_name);
                    //method2(output_path,"-----------设备名称------------");
                    System.out.println(inputStream2String.InputStream2String_Default("设备名称",process.getInputStream()));
                }




                Thread.sleep(pause_time);
                System.out.println("------------Mac地址-------------");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(Mac_address);
                   // method2(output_path,"------------Mac地址-------------");
                    System.out.println(inputStream2String.InputStream2String_Default("Mac地址",process.getInputStream()));
                }

                Thread.sleep(pause_time);
                System.out.println("------------GPU信息-------------");
                System.out.println("需要在手机内安装APP，请打开ADB安装。安装时请点击允许");
                //---------------连接状态判定---------
                process = Runtime.getRuntime().exec(device_list);
                device_online = inputStream2String.InputStream2String_List(process.getInputStream());
                //-----------------------------------
                if (!device_online) {
                    System.out.println("设备已拔出");
                } else {
                    process = Runtime.getRuntime().exec(GPU+directory.getAbsolutePath()+"\\GPU_INFO.apk");
                    // method2(output_path,"------------Mac地址-------------");
                    System.out.println(inputStream2String.InputStream2String_Default("GPU APP 安装情况",process.getInputStream()));
                }
               // System.out.println(inputStream2String.stringBuffer);
                method2(output_path,inputStream2String.stringBuffer);
//                System.out.println("*********************************");
//                inputStream2String.printString();

//            System.out.println("-----安兔兔跑分--------");
//            System.out.println("下载安兔兔跑个分自己看吧");
//            System.out.println("");
//
//
//            System.out.println("-----鲁大师跑分--------");
//            System.out.println("下载鲁大师跑个分自己看吧");
//            System.out.println("");
//
//
//            System.out.println("-----网络制式--------");
//            System.out.println("在包装盒上看吧");
//            System.out.println("");
//
//
//            System.out.println("-----资产编号--------");
//            System.out.println("在贴的盒子上有");
//            System.out.println("");
//
//
//            System.out.println("-----发售年月--------");
//            System.out.println("2020年/9月");
//            System.out.println("");
//
//
//            System.out.println("-----发售价格--------");
//            System.out.println("1999");
//            System.out.println("");

            } catch (IOException | InterruptedException e) {
                System.out.println("请检查ADB是否安装正确");
            }
            System.out.println("文件将在程序退出后保存至"+output_path);
            System.out.println("输入1退出，输入其他任意键继续获取设备信息");
        }
        }


        public static String InputStream2String (InputStream inputStream) throws IOException {
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

            return result;
        }


    public static void method2(String file,StringBuffer stringBuffer){
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file,true)
            ));
            out.write(stringBuffer+"\r\n");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
