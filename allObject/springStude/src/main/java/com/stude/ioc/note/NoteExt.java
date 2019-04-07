package com.stude.ioc.note;

/**
 * Created on 2019/4/7.
 *
 * @author Grak
 * @since 1.0
 */
@ExtService
public class NoteExt {

    public int age =99;
    public String name = "tamo";

    public void show(){
        System.out.println(name + ", age:" + age);
    }

    /**
     * 模拟注解添加bean
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.stude.ioc.note");
        context.setPrototype(true);
        NoteExt ext01 = (NoteExt)context.getBean("noteExt");
        ext01.name = "李白";
        ext01.age = 66;
        NoteExt ext02 = (NoteExt)context.getBean("noteExt");
        ext02.show();
    }
}
