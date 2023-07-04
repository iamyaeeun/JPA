package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //영속
            Member member=em.find(Member.class,150L);
            member.setName("AAAAA");

            em.detach(member); //준영속 상태(영속성 컨텍스트에서 분리했으므로 위에서 setName으로 데이터 변경했어도 트랜잭션 commit 시 update 쿼리 안나감)

            System.out.println("===============");
            tx.commit(); //em.persist(member) 할때가 아닌 commit 할때 DB에 member(엔티티) 저장됨
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
