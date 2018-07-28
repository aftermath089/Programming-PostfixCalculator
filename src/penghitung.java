import java.io.IOException;

/**
 * Created by MY_COMPUTER on 10/04/2017.
 */

public class penghitung {
    //inisialisasi variabel yang akan terpakai
    private String token;   //input user
    private double ans;     //answer
    private int count=0;    //counter
    private char leftP=40;  //left parenthesis

    //saat yang user ketikan salah
    public void error(){
        System.out.println("ada kesalahan dari yang anda ketik ^_^");
    } //output saat input user salah

    //setter token (token adalah persamaan yang diinput user)
    public void setToken(String set){
        token=set;
        processToken(token);    //arahkan ke proses
    }

    //method yang berfungsi untuk menampilkan jawaban
    public void getAns(){
        System.out.println("jawaban: "+ans+"\n");
    }

    //menghilangkan dan mengganti karakter tak diperlukan
    public void processToken(String unproc){
        unproc=unproc.replaceAll(",",".");
        unproc=unproc.replaceAll(" ","");
        unproc=unproc.replaceAll(":","/");



        calculate(unproc);  //arahkan ke calculate
    }

    //mengolah persamaan user menjadi persamaan baru
    public void calculate(String token){
        int length=token.length();
        listChar operator=new listChar();
        listChar outQ=new listChar();

        //selama token belum habis
        while(count!=length) {
            char checkChar = token.charAt(count); //mengetahui char pada posisi ke-'count'

            if(!((checkChar>47&&checkChar<58)||(checkChar==94||checkChar==40||checkChar==41||checkChar==42||checkChar==43||checkChar==45||checkChar==46||checkChar==47))){
                error();
                break;
            }else

            //jika token berupa angka atau tanda titik (1234567890.)
            if((checkChar>47&&checkChar<58)||checkChar==46){
                outQ.lastIn(checkChar); //langsung masukkan ke outputQueue
                count++;
            }else                                                                               //???

            //jika token berupa tanda operator(+-*/^)
            if(checkChar==47||checkChar==43||checkChar==45||checkChar==42||checkChar==94){
                if(!operator.isEmpty()){//selama stack operator tidak kosong
                    if(operator.peekFirst()!=40) {//selama bukan left parenthesis


                        char operator1 = operator.firstOut();   //keluarin yang dari stack
                        char operator2 = checkChar;             //keluarin yang dari queue
                        outQ.lastIn(' ');

                        if(operator1==94){
                            outQ.lastIn(operator1);
                            outQ.lastIn(' ');
                            operator.firstIn(operator2);
                            count++;
                        }else
                        if(operator2==94){
                            operator.firstIn(operator1);
                            operator.firstIn(operator2);
                            count++;
                        }else

                        if (operator1 == 43 || operator1 == 45) {//+-
                            if (operator2 == 43 || operator2 == 45) {//+-
                                outQ.lastIn(operator1);
                                outQ.lastIn(' ');           //kalau di stacknya -, keluarin - ke queue,
                                operator.firstIn(operator2);    //terus masukin + nya ke stack operator
                                count++;
                            } else {
                                operator.firstIn(operator1);    //kalau operator yang terbaca itu */ maka
                                operator.firstIn(operator2);    //masukin tanda +-nya ke stack, diikuti */
                                count++;
                            }


                        }else

                        if (operator1 == 42 || operator1 == 47) {//*/
                            outQ.lastIn(operator1); //yang dari stack dimasukin ke q
                            outQ.lastIn(' ');
                            operator.firstIn(operator2); //yang dari string dimasukin ke stack
                            count++;
                        }
                    }else{//kalau operator di stacknya left parenthesis
                        operator.firstIn(checkChar);    //masukin operator selanjutnya ke stack
                        outQ.lastIn(' ');
                        count++;
                    }
                }
                if(operator.isEmpty()) {//kalo stack operatornya kosong
                    outQ.lastIn(' ');
                    operator.firstIn(checkChar);    //maka operator yang ditemukan dari string bisa langsung dimasukkan ke stack operator
                    count++;
                }
            }

            //kalau tokennya berupa left parenhtesis
            if(checkChar==40){
                operator.firstIn(checkChar);    //push ke stack operator
                count++;
            }

            //kalau tokennya berupa right parenthesis
            if(checkChar==41){
                while(operator.peekFirst()!=leftP){ //sampai menemukan left parenthesis di stack
                    char g=operator.firstOut();     //akan terus 'pop' stack
                    outQ.lastIn(' ');
                    outQ.lastIn(g);                 //dan dimasukkan ke outputQueue
                }
                operator.firstOut();    //membuang left parenthesis karna sudah tidak diperlukan lagi
                count++;
            }
        }

        //kalau token sudah habis namun pada stack operator masih terisi
        while(!operator.isEmpty()){
            char karakter=operator.firstOut();
            if(karakter==40||karakter==41){//kalau masih ada parenthesis padahal token sudah habis, maka terdapat kesalahan input
                error();
                break;
            }else {
                outQ.lastIn(' ');
                outQ.lastIn(karakter);//kalau bukan parenthesis, pop semua operator ke dalam outputQueue

            }
        }
        outQ.lastIn(' ');

        //////////////////////////////////////////////
        /*bagian ini mengeluarkan persamaan POSTFIX.
        String output="";
        char g;
        while(!outQ.isEmpty()){
            g=outQ.firstOut();
            output=output+g;
        }
        System.out.print(output);//di print biar tau gimana bentukannya
        */
        //////////////////////////////////////////////


        //inisiasi linkedlist double
        listDouble doubleQ=new listDouble();

        while(outQ.getSize()!=1){//selama outputQueue tidak empty
            String output="";
            char c;
            double doubleValue;
            int count2=outQ.getSize();

            if(outQ.peekFirst()==' '){//kalau di queuenya ada spasi
                outQ.firstOut();       //maka keluarkan spasi tersebut dari linklist
                count2--;
            }
            while(outQ.peekFirst()!=' '){//selama tidak spasi
                //jika angka
                if(outQ.peekFirst()==46||(outQ.peekFirst()>47&&outQ.peekFirst()<58)) {
                    c = outQ.firstOut();//akan terus mengeluarkan angka selama belum ketemu spasi
                    output = output + c;//angka yang dikeluarkan akan disambungkan satus sama lain
                }
                //jika operator
                if(outQ.peekFirst()==43||outQ.peekFirst()==45||outQ.peekFirst()==42||outQ.peekFirst()==47||outQ.peekFirst()==94) {

                    double temp1 = doubleQ.firstOut();//pop hasil yang ada di stack
                    double temp2 = doubleQ.firstOut();//pop sekali lagi hasil yg ada di stack

                    if (outQ.peekFirst() == 94) {
                        double answer = Math.pow(temp2, temp1);
                        doubleQ.firstIn(answer);
                        outQ.firstOut();
                    } else if (outQ.peekFirst() == 43) {//jika tandanya tambah
                        double answer = temp1 + temp2;      //maka dijumlahkan
                        doubleQ.firstIn(answer);        //dan dimasukan kembali ke stack
                        outQ.firstOut();                //buang tanda tambah tersebut.
                    } else if (outQ.peekFirst() == 45) {//jika tandanya kurang
                        double answer = temp2 - temp1;  //maka dikurangkan
                        doubleQ.firstIn(answer);    //dan dimasukan kembali ke stack
                        outQ.firstOut();            //buang tanda kurang tersebut
                    } else if (outQ.peekFirst() == 42) {//jika tandanya kali
                        double answer = temp1 * temp2;  //maka kalikan
                        doubleQ.firstIn(answer);    //dan dimasukkan kembali ke stack
                        outQ.firstOut();            //buang tanda kali tersebut
                    } else if (outQ.peekFirst() == 47) {//jika operator adalah tanda pembagian
                        if (temp1 == 0) {//jika pembaginya 0
                            error();                            //maka pastinya error
                            System.out.print("dibagi 0?\n");    //diberikan output keterangan
                            outQ.firstOut();                    //membuang tanda bagi tadi dari queue
                        } else {//jika pembagi tidak 0
                            double answer = temp2 / temp1;  //dibagi
                            doubleQ.firstIn(answer);        //masukkan hasil bagi ke doubelQueue
                            outQ.firstOut();                //keluarkan tanda baginya
                        }
                    }

                }
            }
            count2--;
            //jika angka ubah ke desimal lalu push ke doubleQueue.
            if(count2!=1&&output!="") {
                doubleValue = Double.parseDouble(output);
                doubleQ.firstIn(doubleValue);
                outQ.firstOut();            //keluarkan spasi
                count2--;

            }


        }
        if(doubleQ.getSize()!=0) {          //terakhir, jika queuenya berisi
            ans = doubleQ.firstOut();       //keluarkan jawaban ke ans
            getAns();                       //balikkan ke ans
        }

    }
}
