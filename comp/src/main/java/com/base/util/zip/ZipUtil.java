package com.base.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.base.common.exception.app.AppException;


/**
 * 项目名： 创建时间：2017-2-23 创建人：Aobo 类名：ZipUtil 所属包名：com.zip 项目名称：test 类功能描述：
 */
public class ZipUtil {
	private static final List<ZipEntry> entries = new ArrayList<ZipEntry>();

	/**
	 * 将文件压缩为zip
	 * 
	 * @throws Exception
	 */
	public static void zip(String zipFileName, String sourceFileName)
			throws Exception {
		// File zipFile = new File(zipFileName);
		System.out.println("压缩中...");
		// 创建zip输出流
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		// 创建缓冲输出流
		BufferedOutputStream bos = new BufferedOutputStream(out);
		File sourceFile = new File(sourceFileName);
		// 调用函数
		compress(out, bos, sourceFile, sourceFile.getName());
		bos.close();
		out.close();
		System.out.println("压缩完成");
	}

	/**
	 * 读取文件并写入文件流
	 * 
	 * @param out
	 * @param bos
	 * @param sourceFile
	 * @param base
	 * @throws Exception
	 */

	public static void compress(ZipOutputStream out, BufferedOutputStream bos,
			File sourceFile, String base) throws Exception {
		// 如果路径为目录（文件夹）
		if (sourceFile.isDirectory()) {
			// 取出文件夹中的文件（或子文件夹）
			File[] flist = sourceFile.listFiles();
			if (flist.length == 0)// 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
			{
				System.out.println(base + "/");
				out.putNextEntry(new ZipEntry(base + "/"));
			} else {
				// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
				for (int i = 0; i < flist.length; i++) {
					compress(out, bos, flist[i],
							base + "/" + flist[i].getName());
				}
			}
		} else {
			// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
			out.putNextEntry(new ZipEntry(base));
			FileInputStream fos = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fos);
			int tag;
			System.out.println(base);
			// 将源文件写入到zip文件中
			while ((tag = bis.read()) != -1) {
				bos.write(tag);
			}
			bis.close();
			fos.close();
		}
	}

	/**
	 * 
	 * @param zipFileDirectory
	 * @param outputDirectory
	 * @throws ZipException
	 * @throws IOException
	 */
	public static void unZipDirectory(String zipFileDirectory,
			String outputDirectory) throws ZipException, IOException {
		File file = new File(zipFileDirectory);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(".zip")) {
				unzip(zipFileDirectory + File.separator + files[i].getName(),
						outputDirectory);
			}
		}
	}
	/**
	 * 
	 * @param string
	 * @param outputDirectory
	 * @throws ZipException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void unzip(String sourceFile, String outputDirectory)
			throws ZipException, IOException {
		if(!new File(sourceFile).exists()){
			System.out.println("文件不存在！");
			throw new AppException(sourceFile + " 文件不存在！");
		}
		if ("".equals(sourceFile)||sourceFile==null) {
			System.out.println("文件为空！");
			throw new AppException(sourceFile + " 文件为空!");
		}
		if (!sourceFile.toLowerCase().endsWith(".zip")) {
			System.out.println("非zip文件！");
			throw new AppException(sourceFile + " 非zip文件!");
		}
		ZipFile zipFile = new ZipFile(sourceFile);
		Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
		while (enu.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) enu.nextElement();
			if (entry.isDirectory()) {
				String fileName = entry.getName().substring(0,
						entry.getName().length() - 1);
				String directoryPath = outputDirectory + File.separator
						+ fileName;
				File directory = new File(directoryPath);
				if(directory.exists()){
					System.out.println(directoryPath+"文件存在！");
					throw new AppException(directoryPath+"文件存在！");
				}
				directory.mkdir();
			}
			entries.add(entry);
		}
		unzip(zipFile, entries, outputDirectory);
	}

	/**
	 * 
	 * @param zipFile
	 * @param entries2
	 * @param outputDirectory
	 * @throws IOException
	 */
	private static void unzip(ZipFile zipFile, List<ZipEntry> entries,
			String outputDirectory) throws IOException {
		Iterator<ZipEntry> it = entries.iterator();
		while (it.hasNext()) {
			ZipEntry zipEntry = (ZipEntry) it.next();
			MultiThreadEntry mte = new MultiThreadEntry(zipFile, zipEntry,
					outputDirectory);
			Thread thread = new Thread(mte);
			thread.start();
		}
	}

	/**
	 * 读取压缩文件中的内容名称
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<String> readZipFile(String file) throws Exception {
		if(!new File(file).exists()){
			System.out.println("文件不存在！");
			throw new AppException(file + " 文件不存在！");
		}
		List<String> list = new ArrayList<String>();
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				String zeName = new String(ze.getName().getBytes("iso-8859-1"),
						"utf-8");
				list.add(zeName);
			}
		}
		zin.closeEntry();
		return list;
	}

	/**
	 * 删除文件
	 * @param Path
	 */
	public static void deleteFile(String Path) {
		File file = new File(Path);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	
	public static void main(String args[]) throws Exception {
//		List<String> list = ZipUtil
//				.readZipFile("C:\\Users\\Aobo\\Desktop\\MenuTest.zip");
//		for (int i = 0; i < list.size(); i++) {
//			System.err.println(list.get(i));
//		}
		ZipUtil.unzip("C:\\Users\\Aobo\\Desktop\\MenuTest.zip",
				"C:\\Users\\Aobo\\Desktop");
//		ZipUtil.deleteFile("D:\\Temp\\Temp.zip");
	}
}

/**
 * 使用多线程，提高效率
 * 
 * @author 立强
 * 
 */
class MultiThreadEntry implements Runnable {
	public static final int BUFFER_SIZE = 4096;
	private BufferedInputStream bis;
	private ZipEntry zipEntry;
	private String outputDirectory;

	public MultiThreadEntry(ZipFile zipFile, ZipEntry zipEntry,
			String outputDirectory) throws IOException {
		this.zipEntry = zipEntry;
		this.outputDirectory = outputDirectory;
		bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
	}
	public void run() {
		try {
			unzipFiles(zipEntry, outputDirectory);
		} catch (IOException e) {
			try {
				bis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param zipEntry
	 * @param outputDirectory
	 * @throws IOException
	 */
	public void unzipFiles(ZipEntry zipEntry, String outputDirectory)
			throws IOException {
		byte[] data = new byte[BUFFER_SIZE];
		String entryName = zipEntry.getName();
		entryName = new String(entryName.getBytes("GBK"));
		FileOutputStream fos = new FileOutputStream(outputDirectory
				+ File.separator + entryName);
		if (zipEntry.isDirectory()) {

		} else {
			BufferedOutputStream bos = new BufferedOutputStream(fos,
					BUFFER_SIZE);
			int count = 0;
			while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
				bos.write(data, 0, count);
			}
			bos.flush();
			bos.close();
		}
	}
}
