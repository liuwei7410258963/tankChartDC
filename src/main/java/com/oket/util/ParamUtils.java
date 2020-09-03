package com.oket.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 〈参数下发工具类〉
 *
 * @author ysh
 * @create 2019/3/26
 * @since 1.0.0
 */
public class ParamUtils {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 根据传输的长度 往字符串前补0
     *
     * @param data   字符串
     * @param length 总长度
     * @return
     */
    public static String checkData(String data, int length) {
        while (data.length() < length) {
            data = "0" + data;
        }
        return data;
    }

    /**
     * 将字符串按每两位用空格进行分割
     *
     * @param str
     * @return
     */
    public static String CutApart(String str) {
        String regex = "(.{2})";
        str = str.replaceAll(regex, "$1 ");
        return str;
    }

    public static String alternate(String str) {
        char[] cx = str.toCharArray();
        str = "";
        for (int i = 0; i < cx.length; i += 2) {
            str += cx[i];
            str += cx[i + 1];
            str += " ";
        }
        return str;
    }

    /**
     * 油品密度端口号时用到的
     *
     * @param str
     * @return
     */
    public static String alternateByOil(String str) {
        char[] cx = str.toCharArray();
        str = "";
        for (int i = 0; i < cx.length; i += 1) {
            str += cx[i];
            //str += cx[i + 1];
            str += " ";
        }
        return str;
    }

    public static String enUnicode(String str) {// 将汉字转换为16进制数
        String st = "";
        try {
            //这里要非常的注意,在将字符串转换成字节数组的时候一定要明确是什么格式的,这里使用的是gb2312格式的,还有utf-8,ISO-8859-1等格式
            byte[] by = str.getBytes("utf-8");
            for (int i = 0; i < by.length; i++) {
                String strs = Integer.toHexString(by[i]);
                if (strs.length() > 2) {
                    strs = strs.substring(strs.length() - 2);
                }
                st += strs;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("01228990",16));
//        String aa = "30:78:69:61:6f:67:61:6f:20:39:20:31:20:31:20:32:30:31:39:2d:30:37:2d:31:33:20:31:31:3a:31:35:3a:32:34:0a:30:30:30:30:30:30:30:30:20:31:39:30:37:31:32:32:32:2d:50:20:30:30:30:30:30:30:30:30:20:31:2e:30:20:30:2e:33:35:20:31:39:32:2e:31:36:38:2e:31:37:2e:31:37:3a:38:35:30:30:20:34:20:30:38:3a:30:30:20:31:32:3a:30:30:20:31:38:3a:30:30:20:32:32:3a:30:30:20:34:20:30:2e:30:30:31:31:39:30:20:30:2e:30:30:30:38:31:30:20:30:2e:30:30:30:38:31:30:20:30:2e:30:30:30:38:31:30:20:0a:39:39:40:81:3a:72:3c:6a:3a:78:3a:70:3a:6a:3e:78:0a";
//        System.out.println(aa.replaceAll(":",""));
//        String a = "-1,-1,0";
//        System.out.println(a.replaceAll("\\D", ""));
        byte[] a=new byte[]{49,49,0,0};
        try {
            System.out.println(ConvertToASCII("aa",8));
        } catch (Exception e) {
        }

    }

    /**
     * 将float转为低字节在前,高字节在后的byte数组
     */
    public static byte[] toLH(float f) {
        return toLH(Float.floatToRawIntBits(f));
    }

    /**
     * 将int转为低字节在前,高字节在后的byte数组
     */
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }


    public static byte[] hex2Byte(String[] arr) {
        int data[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = Integer.valueOf(arr[i], 16);
            if (data[i] >= 128) {
                data[i] = data[i] - 256;
            }
        }
        byte[] b = new byte[arr.length];
        for (int i = 0; i < data.length; i++) {
            b[i] = Byte.decode("" + data[i]);
        }
        return b;
    }

    public static byte[] intToByteArray1(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * CRC计算
     *
     * @param input
     * @param size
     * @return
     */
    public static byte[] calculateCRC(byte[] input, int size) {
        byte[] ret = new byte[2];
        int i, j;
        int CRC16Lo = 0xff;
        // CRC register
        int CRC16Hi = 0xff;
        int cl = 0x01;
        // 0xA001
        int ch = 0xA0;
        int saveHi, saveLo;
        for (i = 0; i < size; i++) {
            CRC16Lo = CRC16Lo ^ input[i];
            CRC16Lo = CRC16Lo & 0xff;
            for (j = 0; j < 8; j++) {
                saveHi = CRC16Hi;
                saveLo = CRC16Lo;
                CRC16Hi = CRC16Hi >> 1;
                CRC16Lo = CRC16Lo >> 1;
                if ((saveHi & 0x1) == 1) {
                    CRC16Lo = CRC16Lo | 0x80;
                }
                if ((saveLo & 0x1) == 1) {
                    CRC16Hi = CRC16Hi ^ ch;
                    CRC16Lo = CRC16Lo ^ cl;
                }
            }
        }
        ret[1] = (byte) (CRC16Hi & 0xff);
        ret[0] = (byte) (CRC16Lo & 0xff);
        return ret;
    }

    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        // 使用除与取余进行转换
        for (byte b : bytes) {
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }
        System.out.println(buf.length);

        return new String(buf);
    }

    public static byte[] toByte(String cmdStr) {
        if ("".equals(cmdStr)) {
            return null;
        }
        String[] arr = cmdStr.split(" ");
        byte[] cmd = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            cmd[i] = (byte) Integer.parseInt(arr[i], 16);
        }
        return cmd;
    }

    /**
     * 转ASCII码
     *
     * @param str
     * @param length
     * @return
     */
    public static byte[] ConvertToASCII(String str, int length) {
        byte[] sb = new byte[length];
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            sb[i] = (byte) ch[i];
        }
        return sb;
    }


    public static float bytes2FloatOfC(byte[] data) {
        return Float.intBitsToFloat(bytes2IntOfC(data));
    }

    public static Double bytes2FloatOfCRound(byte[] data) {
        Float dataF = Float.intBitsToFloat(bytes2IntOfC(data));
        return Double.valueOf(String.valueOf((float) (Math.round(dataF * 10)) / 10));
    }

    public static int bytes2IntOfC(byte[] b) {
        int s = 0;
        for (int i = 0; i <= 3; i++) {
            s *= 256;
            s += b[i] < 0 ? b[i] + 256 : b[i];
        }
        return s;
    }

    /**
     * 网络参数设置端口号用到的
     *
     * @param b
     * @return
     */
    public static int bytes2IntOfPort(byte[] b) {
        int s = 0;
        for (int i = 0; i < b.length; i++) {
            s *= 256;
            s += b[i] < 0 ? b[i] + 256 : b[i];
        }
        return s;
    }

    public static boolean CheckCRC(byte[] data) {
        byte[] temp = calculateCRC(data, data.length - 2);
        if (temp[0] != data[data.length - 2]) {
            return false;
        }
        if (temp[1] != data[data.length - 1]) {
            return false;
        }
        return true;
    }

    public static String byteToString(byte b) {
        byte high, low;
        byte maskHigh = (byte) 0xf0;
        byte maskLow = 0x0f;
        high = (byte) ((b & maskHigh) >> 4);
        low = (byte) (b & maskLow);
        StringBuffer buf = new StringBuffer();
        buf.append(findHex(high));
        buf.append(findHex(low));
        return buf.toString();
    }

    private static char findHex(byte b) {
        int t = new Byte(b).intValue();
        t = t < 0 ? t + 16 : t;
        if ((0 <= t) && (t <= 9)) {
            return (char) (t + '0');
        }
        return (char) (t - 10 + 'A');
    }

    /**
     * 浮点转换为字节
     *
     * @param f
     * @return
     */
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }
        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;

    }

    public static byte[] chars2Bytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    /**
     * 转换为指定长度的byte[]  (前补0)
     *
     * @param str
     * @param length
     * @return
     */
    public static byte[] toLengthBytes(String str, int length) {
        return chars2Bytes(ParamUtils.checkData(str, length).toCharArray());
    }

    /**
     * String转16进制
     *
     * @param str
     * @return
     */
    public static String StringToHex16String(String str) {
        // String str = "鲸";
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder builder = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            builder.append(chars[bit]);
            bit = bs[i] & 0x0f;
            builder.append(chars[bit]);
            builder.append(' ');
        }
        return builder.toString().trim();
    }

    /**
     * 16进制转String
     *
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * byte[]转换为指定进制字符串
     *
     * @param bytes
     * @param radix
     * @return
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    public static String bytesToAscii(byte[] bytes, int offset, int dateLen) {
        if ((bytes == null) || (bytes.length == 0) || (offset < 0) || (dateLen <= 0)) {
            return null;
        }
        if ((offset >= bytes.length) || (bytes.length - offset < dateLen)) {
            return null;
        }

        String asciiStr = null;
        byte[] data = new byte[dateLen];
        System.arraycopy(bytes, offset, data, 0, dateLen);
        try {
            asciiStr = new String(data, "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        return asciiStr;
    }

    /**
     * 通过时间戳生成8位不重复流水号
     *
     * @param time
     * @return
     */
    public static String timeToHex(long time) {
        return Integer.toHexString((int) time);
    }

    public static String bytesToAscii(byte[] bytes, int dateLen) {
        return bytesToAscii(bytes, 0, dateLen);
    }

    /**
     * byte[]转ascii
     *
     * @param bytes
     * @return
     */
    public static String bytesToAscii(byte[] bytes) {
        return bytesToAscii(bytes, 0, bytes.length);
    }

    /**
     * ascii转String
     */
    public static String asciiToString(String value)
    {
        StringBuffer stringBuffer = new StringBuffer();
        String[] chars = value.split(",");
        for(String c : chars){
            stringBuffer.append((char) Integer.parseInt(c));
        }
        return stringBuffer.toString();
    }


}
