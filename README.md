cordovaendpoint project maps to http://cordovaendpoint-burrsutter.rhcloud.com/

It provides REST endpoints for my Apache Cordova/Phonegap Tutorial (not published).

It includes an example of secured by basic auth JAX-RS endpoint as well.

Open URLs:
http://cordovaendpoint-burrsutter.rhcloud.com/ 
http://cordovaendpoint-burrsutter.rhcloud.com/rest/members

Basic Auth Secured:
http://cordovaendpoint-burrsutter.rhcloud.com/rest/securedmembers
which maps to org.jboss.tools.example.html5.rest.MemberServiceSecured

http://cordovaendpoint-burrsutter.rhcloud.com/securedservlet
which maps to org.jboss.tools.examples.servlet.security.SecuredServlet

User: guest
Password: password

Updated .openshift/config/standalone.xml to have 

          <security-domains>
               <security-domain name="other" cache-type="default">
               <authentication>
                  <login-module code="UsersRoles" flag="required">
                    <module-option name="usersProperties" value="${env.OPENSHIFT_DATA_DIR}/application-users.properties"/>
                    <module-option name="rolesProperties" value="${env.OPENSHIFT_DATA_DIR}/application-roles.properties"/>
                  </login-module>
               </authentication>
               </security-domain>

               <security-domain name="jboss-web-policy" cache-type="default">
                   <authorization>
                       <policy-module code="Delegating" flag="required"/>
                   </authorization>
               </security-domain>
               <security-domain name="jboss-ejb-policy" cache-type="default">
                   <authorization>
                       <policy-module code="Delegating" flag="required"/>
                   </authorization>
               </security-domain>
           </security-domains>

and push it.

Create application-users.properties and application-roles.properties via SSH
in $OPENSHIFT_DATA_DIR

The password in the application-users.properties file can be clear text

Basic Auth is a bit tricky to setup:
- standalone.xml needs to have the appropriate login-module
- jboss-web.xml needs to point to it via security-domain=other in standalone.xml
- web.xml points to the UsersRoles in the standalone.xml
- need to update the two .properties files - via SSH
- JAX-RS code uses @RolesAllowed to map in the role names
