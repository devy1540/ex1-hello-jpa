import hello.jpa.*;
import hello.jpa.Address;
import hello.jpa.Member;
import hello.jpa.Movie;
import hello.jpa.Team;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1", "street1", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "old_street1", "1"));
            member.getAddressHistory().add(new Address("old2", "old_street2", "2"));
            member.getAddressHistory().add(new Address("old3", "old_street3", "3"));

            em.persist(member);
            em.flush();
            em.clear();

            System.out.println("start---");
            Member member1 = em.find(Member.class, member.getId());

            member1.getFavoriteFoods().remove("치킨");
            member1.getFavoriteFoods().add("한식");

            member1.getAddressHistory().remove(new Address("old1", "old_street1", "1"));
            member1.getAddressHistory().add(new Address("newCity1", "old_street1", "1"));


            Address address = new Address("asd", "asd", "asd");
            address.getCity();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("name: " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String name = member.getUsername();
        System.out.println("username: " + name);

        Team team = member.getTeam();
        System.out.println("team: " + team.getName());
    }
}
