package com.firedeluxe.learnenglish;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ArrayList<Word> words;
    ExpandableListView expListView;
    static DatabaseHandler db;
    public static Context context;
    WordAdapter wa;
    private int lastExpandedPosition = -1;
    ImageButton next, previous, test, filter;
    static String CURRECT_UNIT = "unit1";
    static String index = "1";
    Integer CurrectShownLevel = 4; // 4 = all words
    TextView unit_title;
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        db = new DatabaseHandler(this);
        words = db.getAllWords(CURRECT_UNIT);
        wa = new WordAdapter(context, words);
        Word.now = wa.getGroupCount();
        unit_title = (TextView) findViewById(R.id.unit_title);
        unit_title.setText("יחידה 1");
        //face = Typeface.createFromAsset(getAssets(), "fonts/levin.ttf"); Hebrew font
        expListView = (ExpandableListView) findViewById(R.id.words_list);
        expListView.setAdapter(wa);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if (lastExpandedPosition != -1
                        && i != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });

        next = (ImageButton) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (index) {
                    case "1": index = "2A";
                            break;
                    case "2A": index = "2B";
                        break;
                    case "2B": index = "3A";
                        break;
                    case "3A": index = "3B";
                        break;
                    case "3B": index = "4A";
                        break;
                    case "4A": index = "4B";
                        break;
                    case "4B": index = "5A";
                        break;
                    case "5A": index = "5B";
                        break;
                    case "5B": index = "6A";
                        break;
                    case "6A": index = "6B";
                        break;
                    case "6B": index = "7A";
                        break;
                    case "7A": index = "7B";
                        break;
                    case "7B": index = "8A";
                        break;
                    case "8A": index = "8B";
                        break;
                    case "8B": index = "9A";
                        break;
                    case "9A": index = "9B";
                        break;
                    case "9B": index = "10B";
                        break;
                    case "10B": index = "Custom";
                        break;
                    case "Custom": index = "1";
                        break;
                }
                CURRECT_UNIT = "unit"+index;
                if(CURRECT_UNIT.equals("unitCustom")) {
                    unit_title.setText("Custom");
                }else {
                    unit_title.setText(index + " יחידה");
                }
                words = db.getAllWords(CURRECT_UNIT);
                wa.notifyDataSetChanged();
            }
        });
        previous = (ImageButton) findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (index) {
                    case "1": index = "Custom";
                        break;
                    case "2A": index = "1";
                        break;
                    case "2B": index = "2A";
                        break;
                    case "3A": index = "2B";
                        break;
                    case "3B": index = "3A";
                        break;
                    case "4A": index = "3B";
                        break;
                    case "4B": index = "4A";
                        break;
                    case "5A": index = "4B";
                        break;
                    case "5B": index = "5A";
                        break;
                    case "6A": index = "5B";
                        break;
                    case "6B": index = "6A";
                        break;
                    case "7A": index = "6B";
                        break;
                    case "7B": index = "7A";
                        break;
                    case "8A": index = "7B";
                        break;
                    case "8B": index = "8A";
                        break;
                    case "9A": index = "8B";
                        break;
                    case "9B": index = "9A";
                        break;
                    case "10B": index = "9B";
                        break;
                    case "Custom": index = "10B";
                        break;
                }
                CURRECT_UNIT = "unit"+index;
                if(CURRECT_UNIT.equals("unitCustom")) {
                    unit_title.setText("Custom");
                }else {
                    unit_title.setText(index + " יחידה");
                }
                words = db.getAllWords(CURRECT_UNIT);
                wa.notifyDataSetChanged();
            }
        });

        test = (ImageButton) findViewById(R.id.button_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] levels = {"קל", "בינוני", "קשה"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                EditText numberOfWords = new EditText(context);
                numberOfWords.setText("30");
                numberOfWords.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setTitle("בחר רמת מבחן");
                builder.setView(numberOfWords);
                builder.setItems(levels, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Word> temp = buildTest(which, Integer.parseInt(numberOfWords.getText().toString()));
                        if(temp.size() < 5) {
                            Toast.makeText(context, "יש לסמן לפחות 5 מילים קשות ביחידה זו", Toast.LENGTH_LONG).show();
                        } else {
                            Collections.shuffle(temp);
                            Intent i = new Intent(MainActivity.this, TestActivity.class);
                            i.putExtra("arr", temp);
                            startActivity(i);
                        }
                    }
                });
                builder.show();

            }
        });

        filter = (ImageButton) findViewById(R.id.button_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] levels = {"מילים לא מסומנות", "אדום", "צהוב", "ירוק", "הצג הכל"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("מה להציג?");
                builder.setItems(levels, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Word> temp;
                        CurrectShownLevel = which;
                        if(which == 4) {
                            temp = db.getAllWords(CURRECT_UNIT);
                        }else {
                            temp = filter(which);
                        }
                        if(temp.size() == 0) {
                            Toast.makeText(context, "החיפוש לא החזיר תוצאות", Toast.LENGTH_LONG).show();
                        } else {
                            words = temp;
                            wa.notifyDataSetChanged();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public ArrayList<Word> buildTest(int level, int limit) {
        ArrayList<Word> temp = new ArrayList<>();
        int counter = 0;
        for(Word w: words) {
            if(counter == limit && counter != 0) {
                break;
            }
            if(level == 0 && w.getKnowledge().equals("2")) {
                temp.add(w);
                counter++;
            }
            else if(level == 1 && (w.getKnowledge().equals("1") || w.getKnowledge().equals("2"))) {
                temp.add(w);
                counter++;
            }
            else if(level == 2 && w.getKnowledge().equals("1")) {
                temp.add(w);
                counter++;
            }
        }
        return temp;
    }

    public ArrayList<Word> filter(int level) {
        words = db.getAllWords(CURRECT_UNIT);
        if (level == 4){
            return words;
        }
        ArrayList<Word> temp = new ArrayList<>();
        for(Word w: words) {
            if(w.getKnowledge().equals(String.valueOf(level))) {
                temp.add(w);
            }
        }
        return temp;
    }

    public class WordAdapter extends BaseExpandableListAdapter {

        ArrayList myList = new ArrayList();
        LayoutInflater inflater;
        Context context;

        public WordAdapter(){

        }

        public WordAdapter(Context context, ArrayList myList) {
            this.myList = myList;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            return words.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return 1;
        }

        @Override
        public Word getGroup(int i) {
            return words.get(i);
        }

        @Override
        public Word getChild(int i, int i1) {
            return words.get(i);
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int position, boolean b, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_list_layout, parent, false);
                mViewHolder = new MyViewHolder(convertView);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            final Word currentListData = getGroup(position);

            mViewHolder.word_original.setText(currentListData.getOriginal());
            switch (currentListData.getKnowledge()) {
                case "0":   mViewHolder.word_color.setBackgroundColor(Color.parseColor("gray"));
                            break;
                case "1":   mViewHolder.word_color.setBackgroundColor(Color.parseColor("#EF5350"));
                            break;
                case "2":   mViewHolder.word_color.setBackgroundColor(Color.parseColor("#FFEE58"));
                            break;
                case "3":   mViewHolder.word_color.setBackgroundColor(Color.parseColor("#66BB6A"));
                            break;
            }
            notifyDataSetChanged();
            return convertView;
        }

        @Override
        public View getChildView(final int position, int i1, final boolean b, View convertView, final ViewGroup parent) {
            final MyViewChild myViewChild;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.child_item_list_layout, parent, false);
                myViewChild = new MyViewChild(convertView);
                convertView.setTag(myViewChild);
            } else {
                myViewChild = (MyViewChild) convertView.getTag();
            }
            final Word currentListData = getChild(position, 0);
            myViewChild.word_translate.setText(currentListData.getTranslate());
            myViewChild.red.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.setWordKnowledge(String.valueOf(currentListData.get_id()), "1", CURRECT_UNIT);
                    words = filter(CurrectShownLevel);
                    getGroupView(position, b, null, null);
                }
            });
            myViewChild.yellow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.setWordKnowledge(String.valueOf(currentListData.get_id()), "2", CURRECT_UNIT);
                    words = filter(CurrectShownLevel);
                    getGroupView(position, b, null, null);
                }
            });
            myViewChild.green.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.setWordKnowledge(String.valueOf(currentListData.get_id()), "3", CURRECT_UNIT);
                    words = filter(CurrectShownLevel);
                    getGroupView(position, b, null, null);
                }
            });
            notifyDataSetChanged();
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }

    } private class MyViewHolder {
        TextView word_original, word_color;

        public MyViewHolder(View item) {
            word_original = (TextView) item.findViewById(R.id.word);
            word_color = (TextView) item.findViewById(R.id.color);
        }
    }
    private class MyViewChild {
        TextView  word_translate;
        TextView red, yellow, green;

        public MyViewChild(View item) {
            word_translate = (TextView) item.findViewById(R.id.translate);
            word_translate.setTypeface(face);
            red = (TextView) item.findViewById(R.id.button_red);
            yellow = (TextView) item.findViewById(R.id.button_yellow);
            green = (TextView) item.findViewById(R.id.button_green);
        }
    }
}

