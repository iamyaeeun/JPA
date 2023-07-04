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
            Member member=new Member(200L,"member200");
            em.persist(member);

            em.flush(); //flush는 영속성 컨텍스트의 변경내용을 DB에 동기화(트랜잭션 commit 시 flush 자동 호출됨)

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
