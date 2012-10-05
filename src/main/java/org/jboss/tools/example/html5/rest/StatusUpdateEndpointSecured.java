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

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.*;
import org.jboss.tools.example.html5.model.StatusUpdate;

@Stateful
@Path("/securedstatusupdate")
@TransactionAttribute
public class StatusUpdateEndpointSecured
{
   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager em;

   @POST
   @Consumes("application/json")
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
   public StatusUpdate findById(@PathParam("id")
   Long id)
   {
      return em.find(StatusUpdate.class, id);
   }

   @GET
   @Produces("application/json")
   public List<StatusUpdate> listAll()
   {
      @SuppressWarnings("unchecked")
      final List<StatusUpdate> results = em.createQuery("SELECT x FROM StatusUpdate x ORDER BY x.create_time DESC").getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public StatusUpdate update(@PathParam("id")
   Long id, StatusUpdate entity)
   {
      entity.setId(id);
      em.joinTransaction();
      entity = em.merge(entity);
      return entity;
   }
}