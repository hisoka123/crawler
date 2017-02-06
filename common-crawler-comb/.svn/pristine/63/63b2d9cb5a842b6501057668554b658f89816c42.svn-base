package test;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.codec.binary.Base64;

public class ImageSaveTest {
	public static void main(String[] args) throws Exception {
		String edata = "iVBORw0KGgoAAAANSUhEUgAAADIAAAAQCAIAAADbObvbAAABL0lEQVR42tWVOwqEMBCG905ioeewEgtF7GysLETRXrCzsRHBg3gBwVJBBLGwsdNSdgcCIiZmwz5Yd6o8ZjJf/iST2/2SdvtV4mmaVFVlxeI47swVn8JHmqbRdX3rBkGQ5zkeRbR3sdq2tSyL6Ox5XhRF9MTEFAQsjmr7SMMwxnGE3FVV4WsBLox0XUc/Pla1DrnxTQzDIEkStIFGlmXiouu6Koqyl4SyPVa16FhpmoZhiNq+78/zjDuDA0J/etk/ppZpmnVd06884MLj+rBajHfrtZTfUou9QLCgv1JO36xbZ1UAdcuy3FeWJEniON58bNtGD/y7WLhajuMURQENnueXZUGefd9rmobqi+u6ZCxBEM6w0JRwYofwwzqHLgizBYqimGXZVf7Ei37Vf4n1AKEumMqEaL7GAAAAAElFTkSuQmCC";
		byte[] decodeBase64 = Base64.decodeBase64(edata);
		FileUtils.writeByteArrayToFile(new File("C:/Users/Administrator/Desktop/1111.jpg"), decodeBase64);
		System.out.println("done!");
	}
}
