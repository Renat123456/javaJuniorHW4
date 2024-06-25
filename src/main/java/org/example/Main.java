/**
 * Используя hibernate, создать таблицы:
 * 1. Post (публикация) (id, title)
 * 2. PostComment (комментарий к публикации) (id, text, post_id)
 * <p>
 * Написать стандартные CRUD-методы: создание, загрузка, удаление.
 * <p>
 * Доп. задания:
 * 1. * В сущностях post и postComment добавить поля timestamp с датами.
 * 2. * Создать таблицу users(id, name) и в сущностях post и postComment добавить ссылку на юзера.
 * 3. * Реализовать методы:
 * 3.1 Загрузить все комментарии публикации
 * 3.2 Загрузить все публикации по идентификатору юзера
 * 3.3 ** Загрузить все комментарии по идентификатору юзера
 * 3.4 **** По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты.
 * // userId -> List<User>
 * <p>
 * <p>
 * Замечание:
 * 1. Можно использовать ЛЮБУЮ базу данных (например, h2)
 * 2. Если запутаетесь, приходите в группу в телеграме или пишите мне @inchestnov в личку.
 */

package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            withSession(sessionFactory);
        }
    }

    private static void withSession(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Post post = new Post();
            post.setPost("sfew ew3rt34r5t v345v");

            PostComment postComment = new PostComment();
            postComment.setComment("wew 4t34t3 tey4e5y4e5y 45y54y");
            postComment.setPost(post);

            PostComment postComment2 = new PostComment();
            postComment2.setComment("dfhgdrhg ryger etyher trt ey er5y ery e");
            postComment2.setPost(post);

            session.persist(post);
            session.persist(postComment);
            session.persist(postComment2);

            transaction.commit();
        }

        try (Session session = sessionFactory.openSession()) {
            Post post = session.find(Post.class, 1);
            System.out.println(post);
            PostComment postComment = session.find(PostComment.class, 1);
            System.out.println(postComment);
        }

        try (Session session = sessionFactory.openSession()) {
            PostComment toDelete = session.find(PostComment.class, 2);
            Transaction transaction = session.beginTransaction();
            session.remove(toDelete);
            transaction.commit();
        }
    }

    // create read update delete
//    private static void withSessionCRUD(SessionFactory sessionFactory) {
//        try (Session session = sessionFactory.openSession()) {
//            // session <-> statement
//
//            Author author = session.find(Author.class, 1L);
//            System.out.println("Author(1) = " + author);
//        }
//
//        try (Session session = sessionFactory.openSession()) {
//            Transaction tx = session.beginTransaction();
//            Author author = new Author();
//            author.setId(22L);
//            author.setName("Author #22");
//
//            session.persist(author); // insert
//            tx.commit();
//        }
//
//        try (Session session = sessionFactory.openSession()) {
//            Author toUpdate = session.find(Author.class, 22L);
//            session.detach(toUpdate);
//            toUpdate.setName("UPDATED");
//
//            Transaction tx = session.beginTransaction();
////      session.merge(toUpdate); // update
//            tx.commit();
//        }
//
//        try (Session session = sessionFactory.openSession()) {
//            Author toDelete = session.find(Author.class, 1L);
//
//            Transaction tx = session.beginTransaction();
//            session.remove(toDelete); // delete
//            tx.commit();
//        }
//    }
}