package br.com.codeshare.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.codeshare.model.ServiceOrder;

@RequestScoped
public class ServiceOrderRepository extends AbstractRepository<ServiceOrder>{

	public List<ServiceOrder> findClientByName(String nameFilter) {
		TypedQuery<ServiceOrder> query = 
				em.createQuery("from ServiceOrder os join fetch os.client join fetch os.phone where lower (os.client.name) like :name",ServiceOrder.class);
		query.setParameter("name", "%"+nameFilter.toLowerCase()+"%");
		return query.getResultList();
	}
	
	public List<ServiceOrder> findByPhoneId(Long id){
		log.info(String.format("Recovering service orders of phone: %d",id));
		TypedQuery<ServiceOrder> query = 
				em.createNamedQuery(ServiceOrder.FIND_SO_BY_PHONE,ServiceOrder.class);
		query.setParameter("phoneid", id);
		return query.getResultList();
	}
}
