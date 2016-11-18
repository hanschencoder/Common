/*
 * Copyright 2016 Hans Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package site.hanschen.common.utils;

public class ByteUtils {

	private ByteUtils() {
	}

	/**
	 * 把short转化为byte数组，byte[0]是高位
	 *
	 * @param s 需要转换的数值
	 * @return byte数组
	 */
	public static byte[] shortToByteArray(short s) {
		int byteNum = Short.SIZE / 8;
		byte[] targets = new byte[byteNum];
		for (int i = 0; i < byteNum; i++) {
			int offset = (byteNum - 1 - i) * 8;
			targets[i] = (byte) ((s >>> offset) & 0xff);
		}
		return targets;
	}

	/**
	 * 把int转化为byte数组，byte[0]是高位
	 *
	 * @param src 需要转换的数值
	 * @return byte数组
	 */
	public static byte[] intToByteArray(int src) {
		int byteNum = Integer.SIZE / 8;
		byte[] targets = new byte[byteNum];
		for (int i = 0; i < byteNum; i++) {
			int offset = (byteNum - 1 - i) * 8;
			targets[i] = (byte) ((src >>> offset) & 0xff);
		}
		return targets;
	}

	/**
	 * 把long转化为byte数组，byte[0]是高位
	 *
	 * @param src 需要转换的数值
	 * @return byte数组
	 */
	public static byte[] longToByteArray(long src) {
		int byteNum = Long.SIZE / 8;
		byte[] targets = new byte[byteNum];
		for (int i = 0; i < byteNum; i++) {
			int offset = (byteNum - 1 - i) * 8;
			targets[i] = (byte) ((src >>> offset) & 0xff);
		}
		return targets;
	}

	public static byte[] byteMerger(byte[]... arrays) {

		int totalLength = 0;
		for (byte[] array : arrays) {
			if (array != null) {
				totalLength += array.length;
			}
		}

		byte[] targets = new byte[totalLength];
		int dstPos = 0;
		for (byte[] array : arrays) {
			if (array != null) {
				System.arraycopy(array, 0, targets, dstPos, array.length);
				dstPos += array.length;
			}
		}

		return targets;
	}

	public static String dumpByte(byte[] data, int length) {
		if (null == data) {
			return null;
		}
		String print = "";
		for (int i = 0; i < data.length && i < length; i++) {
			byte temp = data[i];
			String hex = Integer.toHexString(temp & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			print = print + "0x" + hex.toUpperCase() + ", ";
		}
		return print;
	}
}
