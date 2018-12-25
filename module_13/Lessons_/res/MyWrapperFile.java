package module_13.Lessons_.res;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyWrapperFile {
    private File file = null;
    private ByteBuffer buffer;
    private int bufferSize = 10_000;
    private long currentPosition;
    private boolean hasFile = false;

    public MyWrapperFile(){
        buffer = ByteBuffer.allocate(bufferSize);
    }
    public MyWrapperFile(File file){
        this.file = file;
        hasFile = true;
    }
    public MyWrapperFile(String filePath){
        file = new File(filePath);
        hasFile = true;
    }

    public void setFile(File file) { this.file = file; hasFile = true; }
    public long getFileLength(){ if (isHasFile()) return file.length(); return 0; }
    public Buffer getBuffer(){ return buffer; }
    public boolean isHasFile(){ return hasFile; }
    public long getCurrentPosition(){ return currentPosition; }

    /*private void updateBuffer(long position) throws IOException {
        if (position < 0) position = 0;
        if (position > getFileLenght()) position = getFileLenght()- bufferSize;
        Path filePath = Paths.get(file.getPath());
        FileChannel readChannel = FileChannel.open(filePath);
        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);

        while(readBuffer.position() < readBuffer.limit()) {
            int noOfBytesRead = readChannel.read(readBuffer, position);
            if (noOfBytesRead > 0) position++;
            if (position > getFileLenght()) break;
        }
        currentPosition = position;
        readChannel.close();
        buffer = readBuffer;
    }*/
    private synchronized void updateBuffer(long position) throws IOException {
        if (position <= 0) position = 1;
        if (position > getFileLength()) position = getFileLength()- bufferSize;
        //Path filePath = Paths.get(file.getPath());
        RandomAccessFile aFile = new RandomAccessFile(file.getPath(), "r");
        FileChannel readChannel = aFile.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);

        int noOfBytesRead = readChannel.read(readBuffer, position);
        System.out.println("noOfBytesRead: " + noOfBytesRead);
        if (noOfBytesRead > 0) position += noOfBytesRead;
        if (position > getFileLength()) position = getFileLength();

        currentPosition = position;
        readChannel.close();
        buffer = readBuffer;
    }

    /** получить содержимое буфера в % от файла - вызывается от главного бегунка
     * */
    public String getBufferedDataByGlobalPercent(int percentage) throws IOException {
        System.out.print("start getBufferedDataByGlobalPercent - " + percentage);
        int oldPosition = buffer.position();
        if (currentPosition > 0 && currentPosition < getFileLength())
            updateBuffer(currentPosition * percentage / 100 - bufferSize/2);
        else if (currentPosition == 0) updateBuffer(0);

        buffer.position(buffer.capacity() /4);
        byte [] bytes = buffer.array();
        String output = new String(bytes).trim();
        System.out.println(" , " + output.length());
        return output;
    }
    public String getBufferedDataByInc() throws IOException {
        return getBufferedData((int)(buffer.position() * (1.05)));
    }
    public String getBufferedDataByDec() throws IOException {
        return getBufferedData((int)(buffer.position() * (0.95)));
    }
    /** получить содержимое буфера по позиции (двигаемся если необходимо)
     * */
    private String getBufferedData(int position) throws IOException {
        System.out.print("start getBufferedData - " + position + " ||");
        if (position > buffer.limit()) position = buffer.limit();
        int oldPosition = buffer.position();
        System.out.println("percent in getBufferedData :" + position * 100 / buffer.capacity());
        if (position * 100 / buffer.capacity() < 25){
            if (currentPosition > 0) updateBuffer(oldPosition - bufferSize/2);
        } else if (position * 100 / buffer.capacity() > 75){
            if (currentPosition < getFileLength()) updateBuffer(oldPosition + bufferSize/2);
        }
        //if (buffer.capacity() == 0);
        byte [] bytes = buffer.array();
        String output = new String(bytes).trim();
        buffer.position(position);
        return output;
    }

}
