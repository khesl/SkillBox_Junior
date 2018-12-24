package module_13.Lessons_.res;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileWriter{
    private FileOutputStream writer;
    private int bufferSize = 1_000_000;

    public MyFileWriter(String path, int bufferSize) throws FileNotFoundException {
        this(path);
        this.bufferSize = bufferSize;
    }
    public MyFileWriter(String path) throws FileNotFoundException {
        writer = new FileOutputStream(path);
    }

    public void flushWriter() throws IOException {
        writer.flush();
        writer.close();
    }

    public synchronized void write(StringBuilder buffer) throws IOException {
        if (buffer.length() > bufferSize * 1.5) throw new IllegalArgumentException("Too long buffer.");
        writer.write(buffer.toString().getBytes());
    }

    public synchronized void write(StringBuffer buffer) throws IOException {
        if (buffer.length() > bufferSize * 1.5) throw new IllegalArgumentException("Too long buffer.");
        writer.write(buffer.toString().getBytes());
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
