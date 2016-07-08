/**
 * Copyright Search Technologies 2011
 */
package com.searchtechnologies.aspire.components;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Element;

import com.searchtechnologies.aspire.framework.*;
import com.searchtechnologies.aspire.services.*;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

/** Sample class for implementing a new Aspire Pipeline Processing stage.
 * 
 * @author Paul Nelson
 */
public class AspireBZip2ExpanderStage extends StageImpl {
  /** The main entry point for processing a job. This will be called, sometimes at the same
   * time by multiple threads (executing multiple jobs), whenever a job needs to be 
   * processed by your component.  
   * @param j  The job to process.
   */
  @Override
  public void process(Job j) throws AspireException {
    InputStream in = Standards.Basic.getContentAsStream(j, this);

    try {
      InputStream deCompressedIn = new BZip2CompressorInputStream(in);
      Standards.Basic.setContentStream(j, deCompressedIn);
    } catch (IOException e) {
      throw new AspireException(this, "ASpireBZip2ExpanderStage.compressor-initialization-problem", e, "Unable to initialize the BZip2 decompressor to wrap the input stream.");
    }

  }


  /** If you use any resources that need to be released when this component is released, 
   * the put them in the close() method. Note:  Don't forget to close your branch
   * handler, if you have one.
   */
  @Override
  public void close() {
  }


  /** Initialize this component with the configuration data from the component manager
   * configuration. NOTE:  This method is *always* called, even if the component
   * manager configuration is empty (in this situation, "config" will be null).
   * 
   * @param config The XML &lt;config&gt; DOM element which holds the custom configuration 
   * for this component from the component manager configuration file. 
   * @throws AspireException
   */
  @Override
  public void initialize(Element config) throws AspireException {
    if(config == null) return;
  }

}
