package module_13.Lessons_.res;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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

    public synchronized int updateBuffer(long position) {
        try {
            if (position <= 0) position = 0;
            if (position + new String(buffer.array()).trim().length() >= getFileLength()) return -1;

            if (position > getFileLength()) position = getFileLength() - bufferSize;
            //Path filePath = Paths.get(file.getPath());
            RandomAccessFile aFile = new RandomAccessFile(file.getPath(), "rw");
            FileChannel readChannel = aFile.getChannel();
            ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);

            int noOfBytesRead = readChannel.read(readBuffer, position);
            System.out.print(" noOfBytesRead: " + noOfBytesRead);
            if (noOfBytesRead > 0) position += noOfBytesRead;
            if (position > getFileLength()) position = getFileLength();

            currentPosition = position;
            System.out.println(" currentPosition: (" + currentPosition + "), ");
            readChannel.close();
            buffer = readBuffer;
            return noOfBytesRead;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void writeToFile(ByteBuffer buffer){
        try {
        RandomAccessFile aFile = new RandomAccessFile(file.getPath(), "w");
        FileChannel readChannel = aFile.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);

        int noOfBytesWritten = readChannel.write(buffer, currentPosition);
        System.out.print(" noOfBytesWritten: " + noOfBytesWritten);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** получить содержимое буфера в % от файла - вызывается от главного бегунка
     * */
    public String getBufferedDataByGlobalPercent(int percentage) {
        System.out.print("start getBufferedDataByGlobalPercent - " + percentage);
        int oldPosition = buffer.position();
        int loadBytes = 0;
        if (currentPosition > 0 && currentPosition < getFileLength()) {
            loadBytes = updateBuffer(currentPosition * percentage / 100 - bufferSize / 2);
        } else if (currentPosition == 0) loadBytes = updateBuffer(0);

        buffer.position(loadBytes / 4);
        byte[] bytes = buffer.array();
        String output = new String(bytes).trim();
        System.out.println(" , " + output.length());
        return output;
    }
    public String getBufferedDataByInc() {
        return getBufferedData((int)(buffer.position() * (1.05)));
    }
    public String getBufferedDataByDec() {
        return getBufferedData((int)(buffer.position() * (0.95)));
    }
    /** получить содержимое буфера по позиции (двигаемся если необходимо)
     * */
    private String getBufferedData(int position) {
        System.out.print("start getBufferedData - " + position + " ||");
        if (position > buffer.limit()) position = buffer.limit();
        int oldPosition = buffer.position();
        System.out.println("percent in getBufferedData :" + position * 100 / buffer.capacity());
        if (position * 100 / buffer.capacity() < 25) {
            if (currentPosition > 0) updateBuffer(oldPosition - bufferSize / 2);
        } else if (position * 100 / buffer.capacity() > 75) {
            if (currentPosition < getFileLength()) updateBuffer(oldPosition + bufferSize / 2);
        }
        //if (buffer.capacity() == 0);
        byte[] bytes = buffer.array();
        String output = new String(bytes).trim();
        buffer.position(position);
        return output;
    }
    /** получить содержимое буфера по позиции (двигаемся если необходимо) в %
     * */
    public String getBufferedDataByLocalPercent(int percent) {
        System.out.print("start getBufferedData - " + percent + " ||");
        //if (position > buffer.limit()) position = buffer.limit();
        int oldPosition = buffer.position();
        int newPosition = percent * buffer.capacity() / 100;
        if (percent < 25) {
            if (currentPosition > 0) updateBuffer(oldPosition - bufferSize / 2);
            newPosition += (50 - percent) * (oldPosition - bufferSize / 2);
        } else if (percent > 75) {
            if (currentPosition < getFileLength()) updateBuffer(oldPosition + bufferSize / 2);
            newPosition -= (50 - percent) * (oldPosition - bufferSize / 2);
        }
        //if (buffer.capacity() == 0);
        byte[] bytes = buffer.array();
        String output = new String(bytes).trim();
        if (newPosition < 0) newPosition = 0;
        if (newPosition > buffer.capacity()) newPosition = buffer.capacity();
        buffer.position(newPosition);
        return output;
    }

}
