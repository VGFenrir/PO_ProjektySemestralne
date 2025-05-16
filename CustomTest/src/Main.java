public class Main {
    public static void main(String[] args){
        CustomList<Integer> cl = new CustomList<>();

        cl.addLast(5);
        cl.addLast(7);
        cl.addLast(512);
        cl.addLast(12);
        cl.addLast(5);
        System.out.println(cl.get(3));

        CustomList<Object> list = new CustomList<>();
        list.add(2.3);
        list.add("aa");
        list.add(2);

        System.out.println(CustomList.filterByClass(list,Number.class));
    }
}
