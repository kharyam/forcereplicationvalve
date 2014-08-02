# Force Replication Valve
Implementation of a tomcat 7 valve that fixes the issue of session not being properly replicated in a cluster.  This behavior was observed when attempting to cluster JSF 2 applications in tomcat.

Update the pom.xml with the version of tomcat that you are using:

    <properties>
        <tomcat.version>7.0.54</tomcat.version>
    </properties>

