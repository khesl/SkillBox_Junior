package module_13.Lessons_.res;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NumGenerator extends Thread{
    private int bufferSize;
    private int minGenerateValue;
    private int maxGenerateValue;
    private String name;
    private MyFileWriter writer_;
    private boolean localWriter = false;

    public NumGenerator(int minGenerateValue, int maxGenerateValue, int bufferSize, String name, MyFileWriter writer){
        if (minGenerateValue > maxGenerateValue) throw new IllegalArgumentException("Min value must be less than Max value.");
        this.minGenerateValue = minGenerateValue;
        this.maxGenerateValue = maxGenerateValue;
        this.bufferSize = bufferSize;
        this.name = name;
        writer_ = writer;
    }
    public NumGenerator(int minGenerateValue, int maxGenerateValue, int bufferSize, String name, String filePath) throws FileNotFoundException {
        if (minGenerateValue > maxGenerateValue) throw new IllegalArgumentException("Min value must be less than Max value.");
        this.minGenerateValue = minGenerateValue;
        this.maxGenerateValue = maxGenerateValue;
        this.bufferSize = bufferSize;
        this.name = name;
        writer_ = new MyFileWriter(filePath);
        localWriter = true;
    }

    @Override
    public void run(){
        while(!interrupted()) {
            System.out.println(name + " start." + "from '" + minGenerateValue + "' to '" + maxGenerateValue + "'");
            long start = System.currentTimeMillis();
            StringBuilder buffer = new StringBuilder();

            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for(int number = minGenerateValue; number < maxGenerateValue; number++){
                //int regionCode = 199;
                for (int regionCode = 100; regionCode < 110; regionCode++)
                    for (char firstLetter : letters)
                    {
                        for (char secondLetter : letters)
                        {
                            for (char thirdLetter : letters) {
                                if (buffer.length() > bufferSize){
                                    try {
                                        long startWrite = System.currentTimeMillis();
                                        writer_.write(buffer);
                                        System.out.println(name + " write " + (System.currentTimeMillis() - startWrite) + " ms");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    buffer = new StringBuilder();
                                }

                                buffer.append(firstLetter);
                                if (number < 10) buffer.append("00");
                                else if (number < 100) buffer.append("0");
                                buffer.append(number)
                                        .append(secondLetter)
                                        .append(thirdLetter);

                                if (regionCode < 10) buffer.append("0");
                                buffer.append(regionCode)
                                        .append('\n');
                            }
                        }
                    }
            }
            try {
                writer_.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (localWriter) {
                try {
                    writer_.flushWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " is done. " + (System.currentTimeMillis() - start) + " ms");
            break;
        }
    }
}