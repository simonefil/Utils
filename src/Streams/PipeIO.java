package Streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

public class PipeIO implements Callable<Boolean> {
    InputStream pIn;
    OutputStream pOut;

    public PipeIO(InputStream parIn, OutputStream parOut) {
        this.pIn = parIn;
        this.pOut = parOut;
    }

    @Override
    public Boolean call() throws IOException {
        boolean tOk = false;
        try {
            byte[] buffer = new byte[8192];
            while (true) {
                int bytesRead = this.pIn.read(buffer, 0, 8192);
                if (bytesRead == -1)
                    break;
                this.pOut.write(buffer, 0, bytesRead);
            }
            tOk = true;
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            this.pIn.close();
            this.pOut.close();
        }

        return tOk;
    }
}
