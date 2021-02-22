public class ADB_Command {
    String adbHome="/Users/jinzelong/Library/Android/sdk/platform-tools/";//adb地址
    //String adbHome_local = ".src/platform-tools/adb";

    //初始化
    String cmd=adbHome+"adb version";//用于检测adb是否可以正常运行（正常显示版本和地址，异常报错）

    //获取设备列表
    String device_list = adbHome+"adb devices";//查询已连接设备、模拟器（空、离线、已连接）

    //获取安卓版本
    String Android_version = adbHome +"adb shell getprop ro.build.version.release";//查询安卓版本

    //CPU
    String Soc_brand = adbHome + "adb shell getprop ro.product.board";//获取SOC型号（System on Chip）
    String Soc_model = adbHome +"adb shell getprop ro.product.cpu.abilist";//获取CPUabi
    String CPU_info = adbHome +"adb shell cat /proc/cpuinfo";//获取CPU详细信息
    String CPU_Speed_MAX = adbHome +"adb shell cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";

    //内存
    String RAM = adbHome + "adb shell cat /proc/meminfo";//获取内存大小

    //屏幕
    String Screen_resolution = adbHome + "adb shell wm size";//查看屏幕分辨率
    String Product_name = adbHome +"adb shell getprop ro.product.name";//获取设备名称
    String Product_model = adbHome + "adb shell getprop ro.product.model";//查看设备信息
    String Screen_density = adbHome + "adb shell wm density";//查看屏幕密度

    //wifi
    //String wifi = adbHome +"adb shell netcfg";//查看网络状态
    String Mac_address = adbHome +"adb shell cat /sys/class/net/wlan0/address";//查看Mac地址（有时候拿不到，要分安卓版本进行测试 ）


    //存储
    String ROM_size = adbHome + "adb shell cat /proc/meminfo";//查看存储大小


    //暂时为空
    //------------------------------------------------------------------------------------
    String Full_Screen;//是不是全面屏（）
    String Screen_size = adbHome +"";//获取屏幕尺寸
    String Android_subVersion = adbHome + "";//查询安卓子版本

}
