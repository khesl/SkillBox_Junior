package module_12.Lessons_.src;

public class LinkLoader extends Thread {
    private boolean pause = false;

    @Override
    public void run(){
        while (!interrupted()){
            if (pause) continue;
            while (MyHttpScannerController.getUniqueLinksProcessed().size() < MyHttpScannerController.getUniqueLinks().size()) {
                try {
                    for (MyHttpScanner_2 scanner : MyHttpScannerController.getScanners()) {
                        System.out.println("\t" + scanner.getStatus());
                    }
                    MyHttpScanner_2 mhs_2 = MyHttpScannerController.getFreeScanner();
                    if (mhs_2 == null) {
                        System.out.print("not free thread.; ");
                        continue;
                    }
                    TreeLinks nextLink = MyHttpScannerController.getNextLink();
                    System.out.println("Next link: " + nextLink);
                    if (nextLink == null) break;

                    mhs_2.resetData(nextLink, MyHttpScannerController.TEMP_URL_PATH_BASE + mhs_2.getScannerName());
                    MyHttpScannerController.getUniqueLinksProcessing().add(nextLink.getHref());
                    mhs_2.start();
                    //mhs_2.join();
                    System.out.print(" sleep..");
                    try {
                        Thread.sleep(Double.valueOf(2000 * Math.random()).intValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(".wake up, ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setPause(boolean pause) { this.pause = pause; }
    public boolean isPause() { return pause; }
}