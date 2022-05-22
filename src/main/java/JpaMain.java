import hello.jpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //persistence.xml에 있는 persistence-unit명
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //데이터의 변경은 하나의 트랜잭션 안에서 실행할 수 있도록 해야한다.

        //JPQL은 엔티티 객체를 대상으로 쿼리 -> 객체지향SQL 이다...
        //SQL은 데이터베이스 테이블을 대상으로 쿼리
        try {
            /*Member member = em.find(Member.class, 1L);
            member.setName("helloJPA");*/

            //JPQL 사용
            /*List<Member> result = em.createQuery("SELECT m FROM Member AS m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for(Member m : result) {
                System.out.println(m);
            }*/
            //비영속
            /*Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");*/

            //영속
            /*System.out.println("===before===");
            em.persist(member);
            System.out.println("===after===");*/
            /*Member tm1 = new Member(10L, "name");
            Member tm2 = new Member(10L, "name");

            //hashcode 비교하면 값이 다름
            System.out.println(tm1.hashCode());
            System.out.println(tm2.hashCode());
            System.out.println(tm1 == tm2);

            Member findMemrber1 = em.find(Member.class, 100L);
            Member findMemrber2 = em.find(Member.class, 100L);

            //hashcode를 비교하면 값이 같음
            System.out.println(findMemrber2 == findMemrber1);
            System.out.println(findMemrber1.hashCode());
            System.out.println(findMemrber2.hashCode());*/

            //NOTE 쓰기 지연으로 commit하는 시점에 SQL이 날라간다.
            /*Member member1 = new Member(150L, "A");
            Member member2 = new Member(151L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("=====");*/
//            Member member = em.find(Member.class, 150L);

            //준영속 상태(EntityManager에서 없애버리는 것) -> 특정 엔티티를 골라서 제어할 수 있음
//            em.detach(member);

            //영속성 컨텍스트를 완전히 비워버림
//            em.clear();

            //영속성 컨텍스트에 아무것도 없어서 쿼리를 다시 날림
//            Member member2 = em.find(Member.class, 150L);

            Member member = new Member("NAME_A");
            em.persist(member);

            System.out.println("=====");

            /*Member member = new Member(200L, "member200");
            em.persist(member);

            //flush를 하게 되면 데이터베이스에 바로 쿼리를 날리는 작업이다.
            // 1차캐시에도 그대로 남는다.
            em.flush();

            System.out.println("====");*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
