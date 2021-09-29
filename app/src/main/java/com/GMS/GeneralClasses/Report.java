package com.GMS.GeneralClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Report {
    Bitmap bitmap, scaleBitmap;
    private final static int PAGE_WIDTH = 1200;
    private final static int PAGE_HEIGHT = 2010;
    static PdfDocument pdfDocument;
    static PdfDocument.PageInfo myPageInfo;
    static Paint myPaint = new Paint();
    static Paint titlePaint = new Paint();

    private static Canvas canvas;
    private final static int TOP_START_TEXT_DRAW = 500;
    private final static int POINT_TABLE_TOP_START = 450;
    private  final static int POINT_TABLE_BOTTOM_END = 530;
    private static int topStartTextDraw = TOP_START_TEXT_DRAW;
    private static int pointTableTopStart = POINT_TABLE_TOP_START;
    private static int pointTableBottomEnd = POINT_TABLE_BOTTOM_END;
    private static final int pointXStartTextDraw = 40;
    private static final int locationXLine = 180;
    private static final int stationXLine = 480;
    private static final int priceXLine = 780;
    private static final int totalXLine = 960;
    private static final int QtyTextStartXPoint = 40;
    private static final int locationTextStartXPoint = 200;
    private static final int stationTextStartXPoint = 500;
    private static final int priceTextStartPoint = 800;
    private static final int totalTextStartXPoint = 980;
    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");


    public static class Pdf
    {
        public static void  create(ArrayList<HistoryItem> mHistoryItems, Context mContext) {
            Report.pdfDocument = new PdfDocument();
            Report.myPageInfo = new PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, 1).create();
            PdfDocument.Page myPage = pdfDocument.startPage(myPageInfo);
            Date date = new Date();
            myPaint.setStrokeWidth(1200);
            titlePaint.setTextAlign(Paint.Align.CENTER);
            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            titlePaint.setTextSize(70);
            canvas = myPage.getCanvas();


            canvas.drawText("Gas Managements System", PAGE_WIDTH / 2, 150, titlePaint);
            titlePaint.setTextSize(35f);
            titlePaint.setTextAlign(Paint.Align.LEFT);
            titlePaint.setStyle(Paint.Style.FILL);
            titlePaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
            canvas.drawText("نوع المستند : تقرير عن الحركات", 750, 250, titlePaint);
            mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-s");
            canvas.drawText(mSimpleDateFormat.format(date), 50, 1950, titlePaint);
            // draw the header of table
            drawCell();
            // draw the title of the head of table
            fillCell(0, "Qty", "Location", "Station", "Price", "Total");

            // fill the data in the each raw
            for (int i = 0; i < mHistoryItems.size(); i++) {
                pointTableTopStart += 80;
                pointTableBottomEnd += 80;
                drawCell();

                fillCell(1, String.valueOf(mHistoryItems.get(i).getQty())
                        , mHistoryItems.get(i).getLocation()
                        , mHistoryItems.get(i).getStation()
                        , String.valueOf(mHistoryItems.get(i).getPrice())
                        , String.valueOf(mHistoryItems.get(i).getPrice() * mHistoryItems.get(i).getQty()));

            }


            //draw the separator line between the title in the head of table
            canvas.drawLine(locationXLine, POINT_TABLE_TOP_START, locationXLine, pointTableBottomEnd, myPaint);
            canvas.drawLine(stationXLine, POINT_TABLE_TOP_START, stationXLine, pointTableBottomEnd, myPaint);
            canvas.drawLine(priceXLine, POINT_TABLE_TOP_START, priceXLine, pointTableBottomEnd, myPaint);
            canvas.drawLine(totalXLine, POINT_TABLE_TOP_START, totalXLine, pointTableBottomEnd, myPaint);


            pdfDocument.finishPage(myPage);
            mSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            File file = new File(Environment.getExternalStorageDirectory(), "/" + mSimpleDateFormat.format(date) + ".pdf");
            try {
                pdfDocument.writeTo(new FileOutputStream(file));
                pdfDocument.close();
                pdfDocument = null;
                myPageInfo = null;
                initMeasurements();
                Toast.makeText(mContext, "pdf has saved", Toast.LENGTH_SHORT).show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private static void drawCell() {

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(2);
            myPaint.setColor(Color.BLACK);

            canvas.drawRect(20, pointTableTopStart, 1180, pointTableBottomEnd, myPaint);

            titlePaint.setTextSize(35f);
            titlePaint.setTextAlign(Paint.Align.LEFT);
            titlePaint.setStyle(Paint.Style.FILL);
            titlePaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

            myPaint.setTextSize(30f);
            myPaint.setTextAlign(Paint.Align.LEFT);
            myPaint.setStyle(Paint.Style.FILL);

        }


        private static void fillCell(int typeCell, String Qty, String location, String station, String price, String Total) {

            if (typeCell == 0) {
                canvas.drawText(Qty, QtyTextStartXPoint, topStartTextDraw, titlePaint);
                canvas.drawText(location, locationTextStartXPoint, topStartTextDraw, titlePaint);
                canvas.drawText(station, stationTextStartXPoint, topStartTextDraw, titlePaint);
                canvas.drawText(price, priceTextStartPoint, topStartTextDraw, titlePaint);
                canvas.drawText(Total, totalTextStartXPoint, topStartTextDraw, titlePaint);
            } else {
                canvas.drawText(Qty, QtyTextStartXPoint, topStartTextDraw, myPaint);
                canvas.drawText(location, locationTextStartXPoint, topStartTextDraw, myPaint);
                canvas.drawText(station, stationTextStartXPoint, topStartTextDraw, myPaint);
                canvas.drawText(price, priceTextStartPoint, topStartTextDraw, myPaint);
                canvas.drawText(Total, totalTextStartXPoint, topStartTextDraw, myPaint);

            }
            topStartTextDraw += 80;
        }

        private static void initMeasurements() {
            topStartTextDraw = TOP_START_TEXT_DRAW;
            pointTableTopStart = POINT_TABLE_TOP_START;
            pointTableBottomEnd = POINT_TABLE_BOTTOM_END;
        }
    }

}