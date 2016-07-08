package com.searchtechnologies.aspire.components;

import com.searchtechnologies.aspire.framework.AXML;
import com.searchtechnologies.aspire.framework.JobFactory;
import com.searchtechnologies.aspire.framework.Standards;
import com.searchtechnologies.aspire.services.*;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestAspireGZExpanderStage extends TestCase {


  public void testAspireGZExpanderStage() throws AspireException, IOException {
    System.setProperty(AspireApplication.ASPIRE_HOME_PROPERTY, "./testdata");
    
    Stage s = new AspireGZExpanderStage();
    
    s.initialize(AXML.stringToDom(
        "<config>" +
        "</config>"
        ));

    AspireObject doc = new AspireObject(Standards.Basic.DOCUMENT_ROOT);

    Job j = JobFactory.newInstance(doc,"TEST-1");
    Standards.Basic.setContentStream(j, new FileInputStream("testdata/gzTest.xml.gz"));

    s.process(j);

    BufferedReader r = new BufferedReader(new InputStreamReader(Standards.Basic.getContentAsStream(j, s)));
    System.out.println(r.readLine());
    assertTrue(r.readLine() != null);
    
    j.close();
  }
}
