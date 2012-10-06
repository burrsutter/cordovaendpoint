/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.tools.example.html5.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import org.jboss.tools.example.html5.model.StatusUpdate;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;

@Path("/secured/statusupdate")
@RequestScoped
@Stateful
public class StatusUpdateEndpointSecured
{
   @PersistenceContext
   private EntityManager em;

   @POST
   @Consumes("application/json")
   @RolesAllowed({"admin","guest"})
   public StatusUpdate create(StatusUpdate entity)
   {
      em.joinTransaction();
      entity.setCreate_time(new java.util.Date()); // default to now
      entity.setVersion(1);
      em.persist(entity);
      return entity;
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   @RolesAllowed({"admin","guest"})
   public StatusUpdate deleteById(@PathParam("id")
   Long id)
   {
      em.joinTransaction();
      StatusUpdate result = em.find(StatusUpdate.class, id);
      em.remove(result);
      return result;
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   @RolesAllowed({"admin","guest"})
   public StatusUpdate findById(@PathParam("id")
   Long id)
   {
      return em.find(StatusUpdate.class, id);
   }

   @GET
   @Produces("application/json")
   @RolesAllowed({"admin","guest"})
   public List<StatusUpdate> listAll()
   {
      @SuppressWarnings("unchecked")
      final List<StatusUpdate> results = em.createQuery("SELECT x FROM StatusUpdate x ORDER BY x.create_time DESC").getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   @RolesAllowed({"admin","guest"})
   public StatusUpdate update(@PathParam("id")
   Long id, StatusUpdate entity)
   {
      entity.setId(id);
      em.joinTransaction();
      entity = em.merge(entity);
      return entity;
   }
}