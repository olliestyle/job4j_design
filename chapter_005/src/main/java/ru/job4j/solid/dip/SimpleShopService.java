package ru.job4j.solid.dip;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SimpleShopService {
    /**
     * 1. Поля. У сервиса есть единственное поле - мапа, для хранения данных.
     * С точки зрения DIP, это нарушение, потому что мы зависим от реализации, а не абстракции.
     * Решение - выделение абстракции для хранилища и уже далее от него нужно будет реализовать InMemoryShopStore
     *
     * public interface ShopStore {
     *
     *     boolean saveUser(User user);
     *     boolean saveOrder(User user, Order order);
     *     Set<Order> getOrders(User user);
     *     Set<User> getUsers();
     *
     * }
     *
     * и в самом сервисе избавиться от зависимости на само хранилище.
     * Тогда можно будет его подменить на любое другое, т.к. нет прямой зависимости.
     *
     * public SimpleShopService(ShopStore shopStore) {
     *     this.shopStore = shopStore;
     * }
     */
    private HashMap<User, Set<Order>> serviceDB = new HashMap<>();

    public boolean createBucket(User user) {
        if (serviceDB.containsKey(user)) {
            return false;
        }
        return serviceDB.put(user, new HashSet<>()) != null;
    }

    public boolean createOrder(User user, Order order) {
        Set<Order> orders = serviceDB.getOrDefault(user, Set.of());
        if (orders.isEmpty()) {
            return false;
        }
        return orders.add(order);
    }

    /**
     * На входные параметры. В данном случае на Order.
     * Во-первых, этот класс нарушает SRP, потому что представляет как саму модель заказа так и АПИ для работы с ней.
     * Во-вторых, он нарушает DIP, потому что опять же сохранение идет в память,
     * нам нужно здесь аналогично абстрагироваться от самого хранилища, создав для него отдельный интерфейс.
     * Но зависимость от хранилища уже будет в сервис заказа, а сервис магазина будет зависеть от сервиса заказов
     *
     * public class SimpleOrderService implements OrderService {
     *
     *     private OrderStore orderStore;
     *
     *     public SimpleOrderStore(OrderStore orderStore) {
     *         this.orderStore = orderStore;
     *     }
     * }
     *
     * // уже в классе ShopService
     *
     * public SimpleShopService(ShopStore shopStore, OrderService orderService) {
     *     this.shopStore = shopStore;
     *     this.orderService = orderService;
     * }
     */
    public boolean addProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderDB.get().add(product);
    }

    private Optional<Order> findOrder(User user, Order order) {
        return serviceDB.getOrDefault(user, Set.of()).stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst();
    }

    public boolean removeProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderDB.get().remove(product.getId());
    }

    public boolean removeOrder(User user, Order order) {
        Set<Order> orders = serviceDB.get(user);
        if (orders == null) {
            return false;
        }
        return orders.remove(order);
    }

    public Check payOrder(User user, Order order) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            /**
             * Стоит обратить внимание на подобные строки.
             * Вы уже знакомы с логгированием, которое проходили в разделе IO.
             * Подобная запись также является нарушением DIP, потому что есть прямая зависимость
             * самого логгирования от реализации, в данном случае оно напрямую зависит от консольного вывода.
             *
             * System.out.println("Get error with "  + user + " " + order);
             * Решение опять использование абстракции для логирования
             *
             * private static final Logger LOGGER = Logger.getLogger("Shop logger");
             */
            System.out.println("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Invalid order");
        }
        if (orderDB.get().isPayed()) {
            System.out.println("Get error with "  + user + " " + order);
            throw new IllegalArgumentException("Already payed!");
        }
        orderDB.get().setPayed(true);
        return new Check((int) (System.currentTimeMillis() % 1000_000), "Payed: " + orderDB.get().getId());
    }
}
