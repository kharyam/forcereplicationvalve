package org.apache.catalina.ha.tcp;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.ha.CatalinaCluster;
import org.apache.catalina.ha.ClusterValve;
import org.apache.catalina.valves.ValveBase;

public class ForceReplicationValve extends ValveBase implements ClusterValve {

  private CatalinaCluster catalinaCluster;

  @Override
  public void invoke(Request request, Response response) throws IOException, ServletException {
    // Touch all session objects so they get replicated across the cluster
    HttpSession session = request.getSession(false);
    for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements(); ) {
      String name = e.nextElement();
      session.setAttribute(name, session.getAttribute(name));
    }
    getNext().invoke(request, response);
  }

  @Override
  public CatalinaCluster getCluster() {
    return catalinaCluster;
  }

  @Override
  public void setCluster(CatalinaCluster newCatalinaCluster) {
    catalinaCluster = newCatalinaCluster;
  }
}