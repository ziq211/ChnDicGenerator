/**
 * Copyright Search Technologies 2016
 */
package com.searchtechnologies.aspire.components;

import com.searchtechnologies.aspire.framework.StageImpl;
import com.searchtechnologies.aspire.framework.Standards;
import com.searchtechnologies.aspire.services.AspireException;
import com.searchtechnologies.aspire.services.Job;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class to decompress GZ file and return the inputstream
 * @author Ziyuan Qin "Atom"
 */
public class AspireGZExpanderStage extends StageImpl {
    /** The main entry point for processing a job. This will be called, sometimes at the same
     * time by multiple threads (executing multiple jobs), whenever a job needs to be
     * processed by your component.
     * @param j  The job to process.
     */
    @Override
    public void process(Job j) throws AspireException {
        InputStream in = Standards.Basic.getContentAsStream(j, this);
        try{
            InputStream gzisDeCompressedIn = new GzipCompressorInputStream(in);
            Standards.Basic.setContentStream(j, gzisDeCompressedIn);
        } catch (IOException e) {
            throw new AspireException(this, "AspireGZExpanderStage.compressor-initialization-problem", e, "Unable to initialize the GZ decompressor to wrap the input stream.");
        }
    }


    @Override
    public void initialize(Element element) throws AspireException {

    }

    @Override
    public void close() {

    }
}
