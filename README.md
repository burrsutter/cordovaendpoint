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
<login-module code="RealmUsersRoles" flag="required">
   <module-option name="usersProperties"
     value="${env.OPENSHIFT_DATA_DIR}/application-users.properties"/>
   <module-option name="rolesProperties" 
     value="${env.OPENSHIFT_DATA_DIR}/application-roles.properties"/>     	   
   <module-option name="realm" value="ApplicationRealm"/>
   <module-option name="password-stacking" value="useFirstPass"/>
</login-module>                        

and pushed it.

Created application-users.properties and application-roles.properties via SSH
in $OPENSHIFT_DATA_DIR

Note: the password in the users file needs to be hashed, so ran the add-user.sh locally
and copied & pasted the hash into the Openshift version of the application-users file.

Basic Auth is a bit tricky to setup:
- standalone.xml needs to have the appropriate login-module
- jboss-web.xml needs to point to it via security-domain=other in standalone.xml
- web.xml points to the RealmUsersRoles in the standalone.xml
- need to update the two .properties files - via SSH
- Code uses @RolesAllowed to map in the role names
