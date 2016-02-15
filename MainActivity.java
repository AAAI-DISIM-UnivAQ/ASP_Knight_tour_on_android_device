package it.univaq.marco.embsasp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import it.unical.mat.embasp.base.ASPHandler;
import it.unical.mat.embasp.base.AnswerSet;
import it.unical.mat.embasp.base.AnswerSetCallback;
import it.unical.mat.embasp.base.AnswerSets;
import it.unical.mat.embasp.dlv.DLVHandler;


public class MainActivity extends Activity implements AnswerSetCallback{

    ASPHandler handler;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = new TextView(this);

        handler = new DLVHandler();

        String input="size(4). forbidden(4,3). must_reach(3,4).#maxint = 99999.even :- size(N), #int(X), N = X+X.:- not even.:- size(N), N < 4.row_col(X) :- #int(X), X >= 1, X <= N, size(N), even.cell(X,Y) :-row_col(X),row_col(Y).move(X1,Y1,X2,Y2) v not_mov(X1,Y1,X2,Y2):-  valid(X1,Y1,X2,Y2).valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X1 = X2+2, Y1 = Y2+1.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X1 = X2+2, Y2 = Y1+1.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X2 = X1+2, Y1 = Y2+1.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X2 = X1+2, Y2 = Y1+1.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X1 = X2+1, Y1 = Y2+2.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X1 = X2+1, Y2 = Y1+2.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X2 = X1+1, Y1 = Y2+2.valid(X1,Y1,X2,Y2) :- cell(X1,Y1), cell(X2,Y2), X2 = X1+1, Y2 = Y1+2.:- cell(X,Y), size(N), not #count{X1,Y1 : move(X,Y,X1,Y1)} = 1.:- cell(X,Y), size(N), not #count{X1,Y1 : move(X1,Y1,X,Y)} = 1.:- forbidden(X,Y), reached(X,Y).\" + \":- must_reach(X,Y), not reached(X,Y).reached(X,Y) :- move(1,1,X,Y).reached(X2,Y2) :- reached(X1,Y1), move(X1,Y1,X2,Y2).:~ cell(X,Y), not forbidden(X,Y), not reached(X,Y).coverage(95).cov(N) :- N <= #count{X,Y : reached(X,Y)} <= M, size(V), coverage(Z),M = V * V, N2 = M * Z, N = N2 /100.";

        try {



            handler.setFilter(Move.class);
            handler.addRawInput(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        handler.start(getApplicationContext(), this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void callback(AnswerSets answerSets) {
        List<AnswerSet> answerSetList=answerSets.getAnswerSetsList();
        String text="";

        for(AnswerSet answerSet:answerSetList){
            try {
                for(Object obj:answerSet.getAnswerObjects())
                    text+=obj.toString()+" ";
            } catch (Exception e) {
                e.printStackTrace();
            }
            text+="\n\n";
        }
        this.text.setText(text);
        setContentView(this.text);
    }
}