package com.crawler.gsxt.htmlparser;


import com.crawler.gsxt.domain.json.*;
import com.crawler.gsxt.domain.json.zhejiang.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GsxtZhejiangParser {

    public AicFeedJson zhejiangResultParser(String resultHtmls, Boolean isDebug) {
        //解析result
        Gson gson = new Gson();
        Map<String, Object> resultHtmlMap = gson.fromJson(resultHtmls, new TypeToken<Map<String, Object>>(){}.getType());

        AicFeedJson gsxtFeedJson = new AicFeedJson();

        //一、工商公示信息
        AicpubInfo gsgsInfo = new AicpubInfo();

        //----------工商公示信息-->登记信息   start-------------
        AicpubRegInfo gsgsDjInfo = new AicpubRegInfo();

        AicpubRegBaseInfo gsgsDjJbInfo = new AicpubRegBaseInfo();
        Object gsgsxx_djxx_jbxx_object = resultHtmlMap.get("gsgsxx_djxx_info");
        if (gsgsxx_djxx_jbxx_object != null) {
            Map<String, Object> gsgsDjMap = (Map<String, Object>) gsgsxx_djxx_jbxx_object;
            if (gsgsDjMap!=null && !gsgsDjMap.isEmpty()) {
                List<AicpubRegChangeInfo> gsgsDjBgInfos = new ArrayList<>();
                List<AicpubRegStohrInfo> gsgsDjGdInfos = new ArrayList<>();
                Object gsgsxx_djxx_first_object = gsgsDjMap.get("gsgsxx_djxx_first");
                if (gsgsxx_djxx_first_object != null) {
                    String gsgsxx_djxx_first = (String) gsgsxx_djxx_first_object;
                    Document gsgsxxDjjbDoc = Jsoup.parse(gsgsxx_djxx_first);
                    Elements gsgsxxDjjbTables = gsgsxxDjjbDoc.select("table");
                    for (Element gsgsxxDjjbTable : gsgsxxDjjbTables) {
                        String gsgsxxDjjbTitle = gsgsxxDjjbTable.select("th").get(0).text();

                        System.out.println("======gsgsxxDjjbTitle====="+gsgsxxDjjbTitle);
                        //1.基本信息
                        if ("基本信息".equals(gsgsxxDjjbTitle)) {
                            Elements ths = gsgsxxDjjbTable.select("th");
                            Elements  tds = gsgsxxDjjbTable.select("td");
                            ths.remove(0);
                            for (int i = 0; i < ths.size(); i++) {
                                String gsgsxxDjjbTh = ths.get(i).text().trim();
                                String gsgsxxDjjbTd = tds.get(i).text();
                                switch (gsgsxxDjjbTh) {
                                    case "注册号":
                                        gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
                                        break;
                                    case "统一社会信用代码/注册号":
                                        gsgsDjJbInfo.setNum(gsgsxxDjjbTd);
                                        break;
                                    case "名称":
                                        gsgsDjJbInfo.setName(gsgsxxDjjbTd);
                                        break;
                                    case "类型":
                                        gsgsDjJbInfo.setType(gsgsxxDjjbTd);
                                        break;
                                    case "法定代表人":
                                        gsgsDjJbInfo.setLegalRepr(gsgsxxDjjbTd);
                                        break;
                                    case "负责人":
                                        gsgsDjJbInfo.setLegalRepr(gsgsxxDjjbTd);
                                        break;
                                    case "注册资本":
                                        gsgsDjJbInfo.setRegCapital(gsgsxxDjjbTd);
                                        break;
                                    case "成立日期":
                                        gsgsDjJbInfo.setRegDateTime(gsgsxxDjjbTd);
                                        break;
                                    case "住所":
                                        gsgsDjJbInfo.setAddress(gsgsxxDjjbTd);
                                        break;
                                    case "经营场所":
                                        gsgsDjJbInfo.setAddress(gsgsxxDjjbTd);
                                        break;
                                    case "营业场所":
                                        gsgsDjJbInfo.setAddress(gsgsxxDjjbTd);
                                        break;
                                    case "营业期限自":
                                        gsgsDjJbInfo.setStartDateTime(gsgsxxDjjbTd);
                                        break;
                                    case "经营期限自":
                                        gsgsDjJbInfo.setStartDateTime(gsgsxxDjjbTd);
                                        break;
                                    case "营业期限至":
                                        gsgsDjJbInfo.setEndDateTime(gsgsxxDjjbTd);
                                        break;
                                    case "经营期限至":
                                        gsgsDjJbInfo.setEndDateTime(gsgsxxDjjbTd);
                                        break;
                                    case "经营范围":
                                        gsgsDjJbInfo.setBusinessScope(gsgsxxDjjbTd);
                                        break;
                                    case "登记机关":
                                        gsgsDjJbInfo.setRegAuthority(gsgsxxDjjbTd);
                                        break;
                                    case "核准日期":
                                        gsgsDjJbInfo.setApprovalDateTime(gsgsxxDjjbTd);
                                        break;
                                    case "登记状态":
                                        gsgsDjJbInfo.setRegStatus(gsgsxxDjjbTd);
                                        break;
                                    case "吊销日期":
                                        gsgsDjJbInfo.setRevokeDate(gsgsxxDjjbTd);
                                        break;
                                    default:
                                        break;
                                }
                            }

                            gsgsDjInfo.setBaseInfo(gsgsDjJbInfo);

                            //3.股东信息
                        } else if (gsgsxxDjjbTitle.contains("股东信息")) {
                            Elements gdxxTrs = gsgsxxDjjbTable.select("tr");
                            gdxxTrs.remove(0);
                            gdxxTrs.remove(0);
                            for (Element gdxxTr : gdxxTrs) {
                                AicpubRegStohrInfo gsgsGdInfo = getGsgsGdInfo(gdxxTr);
                                gsgsDjGdInfos.add(gsgsGdInfo);
                            }

                            //3.变更信息
                        } else if ("变更信息".equals(gsgsxxDjjbTitle)) {
                            Elements gsgsDjBgTrs = gsgsxxDjjbTable.select("tr");
                            gsgsDjBgTrs.remove(0);
                            gsgsDjBgTrs.remove(0);
//                            System.out.println("Tr: " + gsgsDjBgTrs.size());
                            for (Element gsgsDjBgtr : gsgsDjBgTrs) {
                                AicpubRegChangeInfo gsgsBgInfo = getGsgsBgInfo(gsgsDjBgtr);
                                gsgsDjBgInfos.add(gsgsBgInfo);
                            }
                        }
                    }
                }

                System.out.println("=========fist gsgsDjBgInfos.size()==========="+gsgsDjBgInfos.size());

                //变更信息的分页(第二页开始)
                Object gsgsxx_djxx_bg_next_object = gsgsDjMap.get("gsgsxx_djxx_bg_next");
                if (gsgsxx_djxx_bg_next_object != null) {
                    List<String> djxxBgNextList = (List<String>) gsgsxx_djxx_bg_next_object;
                    if (djxxBgNextList!=null && !djxxBgNextList.isEmpty()) {
                        for (String djxx_bg_next : djxxBgNextList) {
                            Document jbBgNextDoc = Jsoup.parse(djxx_bg_next);
                            Elements jbBgNextTables = jbBgNextDoc.select("table");
                            for (Element jbBgNextTable : jbBgNextTables) {
                                String jbBgNextTitle = jbBgNextTable.select("th").get(0).text();

                                if ("变更信息".equals(jbBgNextTitle)) {
                                    Elements gsgsDjBgTrs = jbBgNextTable.select("tr");
                                    gsgsDjBgTrs.remove(0);
                                    gsgsDjBgTrs.remove(0);
                                    gsgsDjBgTrs.remove(gsgsDjBgTrs.last());
                                    for (Element gsgsDjBgtr : gsgsDjBgTrs) {
                                        AicpubRegChangeInfo gsgsBgInfo = getGsgsBgInfo(gsgsDjBgtr);
                                        gsgsDjBgInfos.add(gsgsBgInfo);
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("=========gsgsDjBgInfos.size()==========="+gsgsDjBgInfos.size());
                gsgsDjInfo.setChangeInfos(gsgsDjBgInfos);

                //股东信息的分页(第二页开始)
                Object gsgsxx_djxx_gd_next_object = gsgsDjMap.get("gsgsxx_djxx_gd_next");
                if (gsgsxx_djxx_gd_next_object != null) {
                    List<String> djxxGdNextList = (List<String>) gsgsxx_djxx_gd_next_object;
                    if (djxxGdNextList!=null && !djxxGdNextList.isEmpty()) {
                        for (String djxx_gd_next : djxxGdNextList) {
                            Document jbGdNextDoc = Jsoup.parse(djxx_gd_next);
                            Elements jbGdNextTables = jbGdNextDoc.select("table");
                            for (Element jbGdNextTable : jbGdNextTables) {
                                String jbGdNextTitle = jbGdNextTable.select("th").get(0).text();
                                if (jbGdNextTitle.contains("股东信息")) {
                                    Elements gsgsDjGdTrs = jbGdNextTable.select("tr");
                                    gsgsDjGdTrs.remove(0);
                                    gsgsDjGdTrs.remove(0);
                                    gsgsDjGdTrs.remove(gsgsDjGdTrs.last());
                                    for (Element gsgsDjGdtr : gsgsDjGdTrs) {
                                        AicpubRegStohrInfo gsgsGdInfo = getGsgsGdInfo(gsgsDjGdtr);
                                        gsgsDjGdInfos.add(gsgsGdInfo);
                                    }
                                }
                            }
                        }
                    }
                }
                gsgsDjInfo.setStohrInfos(gsgsDjGdInfos);
                System.out.println("=========gsgsGdGdInfos.size()==========="+gsgsDjGdInfos.size());
            }
        }

        gsgsInfo.setRegInfo(gsgsDjInfo);
        //----------工商公示信息-->登记信息   end-------------


        //----------工商公示信息-->备案信息  start-------------
        AicpubArchiveInfo gsgsBaInfo = new AicpubArchiveInfo();
        //备案信息-->
        List<AicpubArchiveMainDeptInfo> aicpubArchiveMainDeptInfos = new ArrayList<AicpubArchiveMainDeptInfo>();
        Object gsgsxx_baxx_zgbmxx_object = resultHtmlMap.get("gsgsxx_baxx_zgbmxx");
        if (gsgsxx_baxx_zgbmxx_object != null) {
            String gsgsBaZgbmHtml = (String)gsgsxx_baxx_zgbmxx_object;
            Document gsgsBaZgbmDoc = Jsoup.parse(gsgsBaZgbmHtml);
            Elements gsgsBaZgbmTables = gsgsBaZgbmDoc.select("table");
            if (gsgsBaZgbmTables != null && !gsgsBaZgbmTables.isEmpty()) {
                //主管部门（出资人）信息
                Element gsgsBaZgbmTable = gsgsBaZgbmTables.get(0);
                Elements gsgsBaZgbmTrs = gsgsBaZgbmTable.select("tr");
                for (Element gsgsBaZgbmTr : gsgsBaZgbmTrs) {
                    Elements gsgsBaZgbmTds = gsgsBaZgbmTr.select("td");
                    if (gsgsBaZgbmTds.size() == 5) {
                        String type = gsgsBaZgbmTds.get(1).text();
                        String name = gsgsBaZgbmTds.get(2).text();
                        String idType = gsgsBaZgbmTds.get(3).text();
                        String idNum = gsgsBaZgbmTds.get(4).text();
                        AicpubArchiveMainDeptInfo deptInfo = new AicpubArchiveMainDeptInfo();
                        deptInfo.setType(type);
                        deptInfo.setName(name);
                        deptInfo.setIdType(idType);
                        deptInfo.setIdNum(idNum);
                        aicpubArchiveMainDeptInfos.add(deptInfo);
                    }
                }
                gsgsBaInfo.setMainDeptInfo(aicpubArchiveMainDeptInfos);

                //清算信息
                AicpubArchiveClearInfo clearInfo = new AicpubArchiveClearInfo();
                Element qsxxTable = gsgsBaZgbmDoc.getElementById("t32");
                if (qsxxTable != null) {
                    Elements qsxxThs = qsxxTable.select("th");
                    Elements qsxxTds = qsxxTable.select("td");
                    qsxxThs.remove(0);
                    for (int i = 0; i < qsxxThs.size(); i++) {
                        String qsxxTh = qsxxThs.get(i).text().trim();
                        String qsxxTd = qsxxTds.get(i).text();
                        switch (qsxxTh) {
                            case "清算组负责人":
                                clearInfo.setLeader(qsxxTd);
                                break;
                            case "清算组成员":
                                List<String> memList = new ArrayList<String>();
                                memList.add(qsxxTd);
                                clearInfo.setMembers(memList);
                                break;
                            default:
                                break;
                        }
                    }
                }
                gsgsBaInfo.setClearInfo(clearInfo);

            }
        }

        Object gsgsxx_baxx_zyryxx_fzjgxx_object = resultHtmlMap.get("gsgsxx_baxx_info");
        if (gsgsxx_baxx_zyryxx_fzjgxx_object != null) {
            Map<String, Object> baxxDetailMap = (Map<String, Object>)gsgsxx_baxx_zyryxx_fzjgxx_object;
            //主要人员信息
            List<AicpubArchivePrimemberInfo> gsgsBaZyryInfos = new ArrayList<>();
            Object baxxZyry_info_zyry_pages_object = baxxDetailMap.get("gsgsxx_baxx_zyry_pages");
            if (baxxZyry_info_zyry_pages_object != null) {
                List<String> zyry_pages = (List<String>) baxxZyry_info_zyry_pages_object;
                for (String zyryPage : zyry_pages) {
                    Document zyryDoc = Jsoup.parse(zyryPage);
                    Elements zyryTables = zyryDoc.select("table");
                    for (Element zyryTable : zyryTables) {
                        String zyryTitle = zyryTable.select("th").get(0).text();
                        System.out.println("****" + zyryTitle);
                        if (zyryTitle.equals("主要人员信息")) { //主要人员
                            Elements gsgsBaZyryTrs = zyryTables.select("tr");
                            gsgsBaZyryTrs.remove(0);
                            gsgsBaZyryTrs.remove(0);
                            for (Element zyryTr : gsgsBaZyryTrs) {
                                Elements zyryTdElements = zyryTr.select("td");
                                if(zyryTdElements.size() == 3) {
                                    String zyry_name = zyryTdElements.get(1).text();
                                    String zyry_position = zyryTdElements.get(2).text();
                                    if(!"".equals(zyry_name)) {
                                        AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
                                        gsgsBaZyryInfo.setName(zyry_name);
                                        gsgsBaZyryInfo.setPosition(zyry_position);
                                        gsgsBaZyryInfos.add(gsgsBaZyryInfo);
                                    }
                                }

                                if(zyryTdElements.size() == 6) {
                                    String zyry_name = zyryTdElements.get(1).text();
                                    String zyry_position = zyryTdElements.get(2).text();
                                    String zyry_name2 = zyryTdElements.get(4).text();
                                    String zyry_position2 = zyryTdElements.get(5).text();
                                    if(!"".equals(zyry_name)) {
                                        AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
                                        gsgsBaZyryInfo.setName(zyry_name);
                                        gsgsBaZyryInfo.setPosition(zyry_position);
                                        gsgsBaZyryInfos.add(gsgsBaZyryInfo);
                                    }
                                    if(!"".equals(zyry_name2)) {
                                        AicpubArchivePrimemberInfo gsgsBaZyryInfo = new AicpubArchivePrimemberInfo();
                                        gsgsBaZyryInfo.setName(zyry_name2);
                                        gsgsBaZyryInfo.setPosition(zyry_position2);
                                        gsgsBaZyryInfos.add(gsgsBaZyryInfo);
                                    }
                                }
                            }

                        }
                    }
                }
            }
//            System.out.println("############### gsgsBaZyryInfos:" +gsgsBaZyryInfos );
                gsgsBaInfo.setPriMemberInfos(gsgsBaZyryInfos);

            //分支机构信息
            List<AicpubArchiveBranchInfo> fzjgxxList = new ArrayList<>();
            Object gsgs_baxx_fzjg_pages_object = baxxDetailMap.get("gsgsxx_baxx_fzjg_pages");
            if (gsgs_baxx_fzjg_pages_object != null) {
                List<String> fzjg_pages = (List<String>) gsgs_baxx_fzjg_pages_object;
                for (String fzjgPage : fzjg_pages) {
                    Document fzjgDoc = Jsoup.parse(fzjgPage);
                    Elements fzjgTables = fzjgDoc.select("table");
                    for (Element fzjgTable : fzjgTables) {
                        String fzjgTitle = fzjgTable.select("th").get(0).text();
                        System.out.println("****" + fzjgTitle);
                        if (fzjgTitle.equals("分支机构信息")) { //主要人员
                            Elements gsgsBaZyryTrs = fzjgTables.select("tr");
                            gsgsBaZyryTrs.remove(0);
                            gsgsBaZyryTrs.remove(0);
                            for (Element fzjgxxTr : gsgsBaZyryTrs) {
                                Elements fzjgxxTds = fzjgxxTr.select("td");
                                if (fzjgxxTds.size() == 4) {
                                    String num = fzjgxxTds.get(1).text();
                                    String name = fzjgxxTds.get(2).text();
                                    String regAuthority = fzjgxxTds.get(3).text();
                                    AicpubArchiveBranchInfo fzjgxxInfo = new AicpubArchiveBranchInfo();
                                    fzjgxxInfo.setNum(num);
                                    fzjgxxInfo.setName(name);
                                    fzjgxxInfo.setRegAuthority(regAuthority);
                                    fzjgxxList.add(fzjgxxInfo);
                                }
                            }
                        }
                    }
                }
            }
//            System.out.println("############### fzjgxxList:" +fzjgxxList );
            gsgsBaInfo.setBranchInfos(fzjgxxList);

        }

        gsgsInfo.setArchiveInfo(gsgsBaInfo);
        //----------工商公示信息-->备案信息  end-------------


        //----------工商公示信息-->动产抵押登记信息  start-------------
        AicpubChatMortgInfo gsgsDcdydjInfo = new AicpubChatMortgInfo();
        List<AicpubCChatMortgInfo> gsgsDcdydjDcdydjInfos = new ArrayList<AicpubCChatMortgInfo>();
//        List<AicpubCChatMortgDetail> mortgDetails = new ArrayList<AicpubCChatMortgDetail>();
        Object gsgsxx_dcdydjxx_info_object = resultHtmlMap.get("gsgsxx_dcdydjxx_dcdydjxx_info");
        if (gsgsxx_dcdydjxx_info_object != null) {
            List<Map<String, Object>> gsgsDcdydjInfoHtmls = (List<Map<String, Object>>) gsgsxx_dcdydjxx_info_object;
            for (Map<String, Object> dcdyInfoMap : gsgsDcdydjInfoHtmls) {
                //动产抵押登记信息->动产抵押登记信息->详情
                AicpubCChatMortgDetail mortgDetail = new AicpubCChatMortgDetail();
                Object dcdydjxx_detail_page_object = dcdyInfoMap.get("gsgsxx_dcdydjxx_detail_page");
                if (dcdydjxx_detail_page_object != null) {
                    String gsgsxx_dcdydjxx_detail_page = (String) dcdydjxx_detail_page_object;
                    Document gsgsDcdydjDetailDoc = Jsoup.parse(gsgsxx_dcdydjxx_detail_page);
                    Elements gsgsDcdydjDetailTables = gsgsDcdydjDetailDoc.select("table");
                    for (Element gsgsDcdydjDetailTable : gsgsDcdydjDetailTables) {
                        String gsgsDcdydjDetailTitle = gsgsDcdydjDetailTable.select("th").get(0).text();

                        Elements gsgsDcdydjDetailThs = gsgsDcdydjDetailTable.select("th");
                        Elements gsgsDcdydjDetailTds = gsgsDcdydjDetailTable.select("td");
                        gsgsDcdydjDetailThs.remove(0);
                        System.out.println("###: " + gsgsDcdydjDetailTitle);
                        //动产抵押登记信息
                        if ("动产抵押登记信息".equals(gsgsDcdydjDetailTitle)) {
                            AicpubCChatMortgRegInfo mortgRegInfo = new AicpubCChatMortgRegInfo();

                            for (int i = 0; i < gsgsDcdydjDetailThs.size(); i++) {
                                String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(i).text().replace(" ", "");
                                String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(i).text().replace(" ", "");

                                switch (gsgsDcdydjDetailTh) {
                                    case "登记编号":
                                        mortgRegInfo.setRegNum(gsgsDcdydjDetailTd);
                                        break;
                                    case "登记日期":
                                        mortgRegInfo.setRegDate(gsgsDcdydjDetailTd);
                                        break;
                                    case "登记机关":
                                        mortgRegInfo.setRegAuthority(gsgsDcdydjDetailTd);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            mortgDetail.setMortgRegInfo(mortgRegInfo);
                            //获取动产抵押登记信息详情  抵押权人概况
                        } else if ("抵押权人概况".equals(gsgsDcdydjDetailTitle)) {
                            List<AicpubCChatMortgPersonInfo> mortgPersonInfos = new ArrayList<AicpubCChatMortgPersonInfo>();
                            if (gsgsDcdydjDetailTds.size() == 4) {
                                String name = gsgsDcdydjDetailTds.get(1).text();
                                String type = gsgsDcdydjDetailTds.get(2).text();
                                String num = gsgsDcdydjDetailTds.get(3).text();

                                AicpubCChatMortgPersonInfo mortgPersonInfo = new AicpubCChatMortgPersonInfo();
                                mortgPersonInfo.setName(name);
                                mortgPersonInfo.setType(type);
                                mortgPersonInfo.setNum(num);

                                mortgPersonInfos.add(mortgPersonInfo);
                            }
                            mortgDetail.setMortgPersonInfos(mortgPersonInfos);
                            //动产抵押登记信息->详情->抵押物概况
                        } else if ("抵押物概况".equals(gsgsDcdydjDetailTitle)) {
                            List<AicpubCChatMortgGoodsInfo> mortgGoodsInfos = new ArrayList<AicpubCChatMortgGoodsInfo>();

                            if (gsgsDcdydjDetailTds.size() == 5) {
                                String name = gsgsDcdydjDetailTds.get(1).text();
                                String ownerShip = gsgsDcdydjDetailTds.get(2).text();
                                String generalSituation = gsgsDcdydjDetailTds.get(3).text();
                                String note = gsgsDcdydjDetailTds.get(4).text();

                                AicpubCChatMortgGoodsInfo mortgGoodsInfo = new AicpubCChatMortgGoodsInfo();
                                mortgGoodsInfo.setName(name);
                                mortgGoodsInfo.setOwnerShip(ownerShip);
                                mortgGoodsInfo.setGeneralSituation(generalSituation);
                                mortgGoodsInfo.setNote(note);

                                mortgGoodsInfos.add(mortgGoodsInfo);
                            }
                            mortgDetail.setMortgGoodsInfos(mortgGoodsInfos);
                            //动产抵押登记信息->详情->被担保债权概况
                        } else if ("被担保债权概况".equals(gsgsDcdydjDetailTitle)) {
                            AicpubCChatMortgGuaranteedInfo mortgGuaranteedInfo = new AicpubCChatMortgGuaranteedInfo();
                            for (int i = 0; i < gsgsDcdydjDetailThs.size(); i++) {
                                String gsgsDcdydjDetailTh = gsgsDcdydjDetailThs.get(i).text().replace(" ", "");
                                String gsgsDcdydjDetailTd = gsgsDcdydjDetailTds.get(i).text().replace(" ", "");

                                switch (gsgsDcdydjDetailTh) {
                                    case "种类":
                                        mortgGuaranteedInfo.setCategory(gsgsDcdydjDetailTd);
                                        break;
                                    case "数额":
                                        mortgGuaranteedInfo.setAmount(gsgsDcdydjDetailTd);
                                        break;
                                    case "担保的范围":
                                        mortgGuaranteedInfo.setGuarantyScope(gsgsDcdydjDetailTd);
                                        break;
                                    case "债务人履行债务的期限":
                                        mortgGuaranteedInfo.setTerm(gsgsDcdydjDetailTd);
                                        break;
                                    case "备注":
                                        mortgGuaranteedInfo.setNote(gsgsDcdydjDetailTd);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            mortgDetail.setMortgGuaranteedInfo(mortgGuaranteedInfo);
                        }
                    }

                }

                //动产抵押登记信息->动产抵押登记信息
                Object dcdydjxx_tr_object = dcdyInfoMap.get("gsgsxx_dcdydjxx_dcdydjxx_tr");
                if (dcdydjxx_tr_object != null) {
                    String dcdydjxx_tr = (String) dcdydjxx_tr_object;
                    System.out.println("####tr: " + dcdydjxx_tr);
                    String[] trStr = dcdydjxx_tr.split("</td>");
                    if (trStr.length == 8){
                        AicpubCChatMortgInfo gsgsDcdydjDcdydjInfo = new AicpubCChatMortgInfo();
                        String regNumStr = trStr[1];
                        String regNumText = regNumStr.substring(regNumStr.lastIndexOf(">") + 1, regNumStr.length()).trim();
                        String regDateStr = trStr[2];
                        String regDateText = regDateStr.substring(regDateStr.lastIndexOf(">") + 1, regDateStr.length()).trim();
                        String regAuthorityStr = trStr[3];
                        String regAuthorityText = regAuthorityStr.substring(regAuthorityStr.lastIndexOf(">") + 1, regAuthorityStr.length()).trim();
                        String bdbzqAmountStr = trStr[4];
                        String bdbzqAmountText = bdbzqAmountStr.substring(bdbzqAmountStr.lastIndexOf(">") + 1, bdbzqAmountStr.length()).trim();
                        String statusStr = trStr[5];
                        String statusText = statusStr.substring(statusStr.lastIndexOf(">") + 1, statusStr.length()).trim();

                        String regNum = regNumText;
                        String regDate = regDateText;
                        String reg_Authority = regAuthorityText;
                        String bdbzqAmount = bdbzqAmountText;
                        String status = statusText;
                        gsgsDcdydjDcdydjInfo.setRegNum(regNum);
                        gsgsDcdydjDcdydjInfo.setRegDateTime(regDate);
                        gsgsDcdydjDcdydjInfo.setRegAuthority(reg_Authority);
                        gsgsDcdydjDcdydjInfo.setGuaranteedDebtAmount(bdbzqAmount);
                        gsgsDcdydjDcdydjInfo.setStatus(status);
                        gsgsDcdydjDcdydjInfo.setMortgDetail(mortgDetail);
//                        System.out.println("====gsgsDcdydjDcdydjInfo====" + gsgsDcdydjDcdydjInfo);
//                        System.out.println("====MortgDetail====" + gsgsDcdydjDcdydjInfo.getMortgDetail());
                        gsgsDcdydjDcdydjInfos.add(gsgsDcdydjDcdydjInfo);
                    }
                }
            }
        }
//        System.out.println("***动产抵押信息：" + gsgsDcdydjDcdydjInfos);
        gsgsDcdydjInfo.setChatMortgInfos(gsgsDcdydjDcdydjInfos);
        gsgsInfo.setChatMortgInfo(gsgsDcdydjInfo);
        //----------工商公示信息-->动产抵押登记信息  end-------------


        //----------工商公示信息-->股权出质登记信息  start------------
        AicpubEqumortgregInfo gsgsGqczdjInfo = new AicpubEqumortgregInfo();
        List<AicpubEEqumortgregInfo> gsgsGqczdjGqczdjInfos = new ArrayList<AicpubEEqumortgregInfo>();
        Object gsgsxx_gqczdjxx_gqczdjxx_object = resultHtmlMap.get("gsgsxx_gqczdjxx_gqczdjxx");
        if (gsgsxx_gqczdjxx_gqczdjxx_object != null) {
            String gsgsGqczdjHtml = (String)gsgsxx_gqczdjxx_gqczdjxx_object;
            Document gsgsGqczdjDoc = Jsoup.parse(gsgsGqczdjHtml);
            Elements gsgsGqczdjTables = gsgsGqczdjDoc.select("table");
            if (gsgsGqczdjTables != null && !gsgsGqczdjTables.isEmpty()) {
                Element gsgsGqczdjTable = gsgsGqczdjTables.get(0);
                Elements gsgsGqczdjTrs = gsgsGqczdjTable.select("tr");
                gsgsGqczdjTrs.remove(0);
                gsgsGqczdjTrs.remove(0);
                for (Element gsgsGqczdjTr : gsgsGqczdjTrs) {
                    Elements gsgsGqczdjTds = gsgsGqczdjTr.select("td");

                    AicpubEEqumortgregInfo gsgsGqczdjGqczdjInfo = new AicpubEEqumortgregInfo();

                    if (gsgsGqczdjTds.size() == 10) {
                        String regNum = gsgsGqczdjTds.get(1).text();
                        String czr = gsgsGqczdjTds.get(2).text();
                        String czrIdNum = gsgsGqczdjTds.get(3).text();
                        String czgqAmount = gsgsGqczdjTds.get(4).text();
                        String zqr = gsgsGqczdjTds.get(5).text();
                        String zqrIdNum = gsgsGqczdjTds.get(6).text();
                        String gqczsldjDate = gsgsGqczdjTds.get(7).text();
                        String status = gsgsGqczdjTds.get(8).text();
                        String changeSitu = gsgsGqczdjTds.get(9).text();
                        gsgsGqczdjGqczdjInfo.setRegNum(regNum);
                        gsgsGqczdjGqczdjInfo.setMortgagorName(czr);
                        gsgsGqczdjGqczdjInfo.setMortgagorIdNum(czrIdNum);
                        gsgsGqczdjGqczdjInfo.setMortgAmount(czgqAmount);
                        gsgsGqczdjGqczdjInfo.setMortgageeName(zqr);
                        gsgsGqczdjGqczdjInfo.setMortgageeIdNum(zqrIdNum);
                        gsgsGqczdjGqczdjInfo.setRegDateTime(gqczsldjDate);
                        gsgsGqczdjGqczdjInfo.setStatus(status);
                        gsgsGqczdjGqczdjInfo.setChangeSitu(changeSitu);

                    } else if (gsgsGqczdjTds.size() == 11) {
                        String regNum = gsgsGqczdjTds.get(1).text();
                        String czr = gsgsGqczdjTds.get(2).text();
                        String czrIdNum = gsgsGqczdjTds.get(3).text();
                        String czgqAmount = gsgsGqczdjTds.get(4).text();
                        String zqr = gsgsGqczdjTds.get(5).text();
                        String zqrIdNum = gsgsGqczdjTds.get(6).text();
                        String gqczsldjDate = gsgsGqczdjTds.get(7).text();
                        String status = gsgsGqczdjTds.get(8).text();
                        String pubDate = gsgsGqczdjTds.get(9).text();
                        String changeSitu = gsgsGqczdjTds.get(10).text();
                        gsgsGqczdjGqczdjInfo.setRegNum(regNum);
                        gsgsGqczdjGqczdjInfo.setMortgagorName(czr);
                        gsgsGqczdjGqczdjInfo.setMortgagorIdNum(czrIdNum);
                        gsgsGqczdjGqczdjInfo.setMortgAmount(czgqAmount);
                        gsgsGqczdjGqczdjInfo.setMortgageeName(zqr);
                        gsgsGqczdjGqczdjInfo.setMortgageeIdNum(zqrIdNum);
                        gsgsGqczdjGqczdjInfo.setRegDateTime(gqczsldjDate);
                        gsgsGqczdjGqczdjInfo.setStatus(status);
                        gsgsGqczdjGqczdjInfo.setPubDate(pubDate);
                        gsgsGqczdjGqczdjInfo.setChangeSitu(changeSitu);
                    }

                    gsgsGqczdjGqczdjInfos.add(gsgsGqczdjGqczdjInfo);
                }

                if(isDebug) {
                    gsgsGqczdjInfo.setHtml(gsgsGqczdjTable.toString());
                }
            }
        }

//        System.out.println("=======gsgsGqczdjGqczdjInfos======"+gsgsGqczdjGqczdjInfos);

        gsgsGqczdjInfo.setEqumortgregInfos(gsgsGqczdjGqczdjInfos);
        gsgsInfo.setEquMortgRegInfo(gsgsGqczdjInfo);
        //----------工商公示信息-->股权出质登记信息  end-------------


        //----------工商公示信息-->行政处罚信息  start-------------
        //行政处罚信息
        AicpubAdmpunishInfo gsgsXzcfInfo = new AicpubAdmpunishInfo();
        List<AicpubAAdmpunishInfo> gsgsXzcfXzcfInfos = new ArrayList<AicpubAAdmpunishInfo>();
        Object gsgsxx_xzcfxx_xzcfxx_object = resultHtmlMap.get("gsgsxx_xzcfxx_xzcfxx");
        if (gsgsxx_xzcfxx_xzcfxx_object != null) {
            String gsgsXzcfHtml = (String)gsgsxx_xzcfxx_xzcfxx_object;
            Document gsgsXzcfDoc = Jsoup.parse(gsgsXzcfHtml);
            Elements gsgsXzcfTables = gsgsXzcfDoc.select("table");
            if (gsgsXzcfTables != null && !gsgsXzcfTables.isEmpty()) {
                Element gsgsXzcfTable = gsgsXzcfTables.get(0);
                Elements gsgsXzcfTrs = gsgsXzcfTable.select("tr");
                gsgsXzcfTrs.remove(0);
                gsgsXzcfTrs.remove(0);

                int i = 0;
                for (Element gsgsXzcfXzcfTr : gsgsXzcfTrs) {
                    Elements gsgsGqczdjTds = gsgsXzcfXzcfTr.select("td");

                    if (gsgsGqczdjTds.size() == 7) {
                        String xzcfjdsNum = gsgsGqczdjTds.get(1).text();
                        String wfxwType = gsgsGqczdjTds.get(2).text();
                        String xzcfContent = gsgsGqczdjTds.get(3).text();
                        String zcxzcfjdjgName = gsgsGqczdjTds.get(4).text();
                        String zcxzcfjdDate = gsgsGqczdjTds.get(5).text();

                        AicpubAAdmpunishInfo gsgsXzcfXzcfInfo = new AicpubAAdmpunishInfo();
                        gsgsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
                        gsgsXzcfXzcfInfo.setIllegalActType(wfxwType);
                        gsgsXzcfXzcfInfo.setPunishContent(xzcfContent);
                        gsgsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
                        gsgsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
                        gsgsXzcfXzcfInfos.add(gsgsXzcfXzcfInfo);
                    }
                }

                if(isDebug) {
                    gsgsXzcfInfo.setHtml(gsgsXzcfTable.toString());
                }
            }
        }

        gsgsXzcfInfo.setAdmPunishInfos(gsgsXzcfXzcfInfos);
        gsgsInfo.setAdmPunishInfo(gsgsXzcfInfo);
        //----------工商公示信息-->行政处罚信息  end-------------

        //----------工商公示信息-->经营异常信息  start-------------
        AicpubOperanomaInfo gsgsJyycInfo = new AicpubOperanomaInfo();
        List<AicpubOOperanomaInfo> gsgsJyycJyycInfos = new ArrayList<AicpubOOperanomaInfo>();
        Object gsgsxx_jyycxx_jyycxx_object = resultHtmlMap.get("gsgsxx_jyycxx_jyycxx");
        if (gsgsxx_jyycxx_jyycxx_object != null) {
            String gsgsJyycHtml = (String)gsgsxx_jyycxx_jyycxx_object;
            Document gsgsJyycDoc = Jsoup.parse(gsgsJyycHtml);
            Elements gsgsJyycTables = gsgsJyycDoc.select("table");
            if (gsgsJyycTables != null && !gsgsJyycTables.isEmpty()) {
                Element gsgsJyycTable = gsgsJyycTables.get(0);
                Elements gsgsJyycTrs = gsgsJyycTable.select("tr");
                gsgsJyycTrs.remove(0);
                gsgsJyycTrs.remove(0);
                for (Element gsgsJyycTr : gsgsJyycTrs) {
                    Elements gsgsJyycTds = gsgsJyycTr.select("td");

                    AicpubOOperanomaInfo gsgsJyycJyycInfo = new AicpubOOperanomaInfo();
                    if (gsgsJyycTds.size() == 6) {
                        String lrjyycmlCause = gsgsJyycTds.get(1).text();
                        String lrDate = gsgsJyycTds.get(2).text();
                        String ycjyycmlCause = gsgsJyycTds.get(3).text();
                        String ycDate = gsgsJyycTds.get(4).text();
                        String zcjdAuthority = gsgsJyycTds.get(5).text();

                        gsgsJyycJyycInfo.setIncludeCause(lrjyycmlCause);
                        gsgsJyycJyycInfo.setIncludeDateTime(lrDate);
                        gsgsJyycJyycInfo.setRemoveCause(ycjyycmlCause);
                        gsgsJyycJyycInfo.setRemoveDateTime(ycDate);
                        gsgsJyycJyycInfo.setRemoveAuthority(zcjdAuthority);
                    }
                    gsgsJyycJyycInfos.add(gsgsJyycJyycInfo);
                }

                if(isDebug) {
                    gsgsJyycInfo.setHtml(gsgsJyycTable.toString());
                }
            }
        }

        gsgsJyycInfo.setOperAnomaInfos(gsgsJyycJyycInfos);
        gsgsInfo.setOperAnomaInfo(gsgsJyycInfo);
        //-----------------工商公示信息-->经营异常信息 end-----------------------

        //-----------------工商公示信息-->严重违法信息 start-----------------------
        AicpubSerillegalInfo gsgsYzwfInfo = new AicpubSerillegalInfo();
        List<AicpubSSerillegalInfo> gsgsYzwfYzwfInfos = new ArrayList<AicpubSSerillegalInfo>();
        Object gsgsxx_yzwfxx_yzwfxx_object = resultHtmlMap.get("gsgsxx_yzwfxx_yzwfxx");
        if (gsgsxx_yzwfxx_yzwfxx_object != null) {
            String gsgsYzwfHtml = (String)gsgsxx_yzwfxx_yzwfxx_object;
            Document gsgsYzwfDoc = Jsoup.parse(gsgsYzwfHtml);
            Elements gsgsYzwfTables = gsgsYzwfDoc.select("table");
            if (gsgsYzwfTables != null && !gsgsYzwfTables.isEmpty()) {
                Element gsgsYzwfTable = gsgsYzwfTables.get(0);
                Elements gsgsYzwfTrs = gsgsYzwfTable.select("tr");
                gsgsYzwfTrs.remove(0);
                gsgsYzwfTrs.remove(0);
                for (Element gsgsYzwfTr : gsgsYzwfTrs) {
                    Elements gsgsYzwfTds = gsgsYzwfTr.select("td");
                    AicpubSSerillegalInfo gsgsYzwfYzwfInfo = new AicpubSSerillegalInfo();

                    if (gsgsYzwfTds.size() == 6) {
                        String lryzwfqymdCause = gsgsYzwfTds.get(1).text();
                        String lrDate = gsgsYzwfTds.get(2).text();
                        String ycyzwfqymdCause = gsgsYzwfTds.get(3).text();
                        String ycDate = gsgsYzwfTds.get(4).text();
                        String zcjdAuthority = gsgsYzwfTds.get(5).text();

                        gsgsYzwfYzwfInfo.setIncludeCause(lryzwfqymdCause);
                        gsgsYzwfYzwfInfo.setIncludeDateTime(lrDate);
                        gsgsYzwfYzwfInfo.setRemoveCause(ycyzwfqymdCause);
                        gsgsYzwfYzwfInfo.setRemoveDateTime(ycDate);
                        gsgsYzwfYzwfInfo.setDeciAuthority(zcjdAuthority);
                    }
                    gsgsYzwfYzwfInfos.add(gsgsYzwfYzwfInfo);
                }

                if(isDebug) {
                    gsgsYzwfInfo.setHtml(gsgsYzwfTable.toString());
                }
            }

        }
        gsgsYzwfInfo.setSerIllegalInfos(gsgsYzwfYzwfInfos);
        gsgsInfo.setSerIllegalInfo(gsgsYzwfInfo);
        //-----------------工商公示信息-->严重违法信息 end-----------------------

        //-----------------工商公示信息-->抽查检查信息 start-----------------------
        AicpubCheckInfo gsgsCcjcInfo = new AicpubCheckInfo();
        List<AicpubCCheckInfo> gsgsCcjcCcjcInfos = new ArrayList<AicpubCCheckInfo>();
        Object gsgsxx_ccjcxx_ccjcxx_object = resultHtmlMap.get("gsgsxx_ccjcxx_ccjcxx");
        if (gsgsxx_ccjcxx_ccjcxx_object != null) {
            String gsgsCcjcHtml = (String)gsgsxx_ccjcxx_ccjcxx_object;
            Document gsgsCcjcDoc = Jsoup.parse(gsgsCcjcHtml);
            Element chouchaxinxiDiv = gsgsCcjcDoc.getElementById("chouchaxinxi");
            Elements gsgsCcjcTables = chouchaxinxiDiv.select("table");
            if (gsgsCcjcTables != null && !gsgsCcjcTables.isEmpty()) {
                Element gsgsCcjcTable = gsgsCcjcTables.get(0);
                Elements gsgsCcjcTrs = gsgsCcjcTable.select("tr");
                gsgsCcjcTrs.remove(0);
                gsgsCcjcTrs.remove(0);
                for (Element gsgsCcjcTr : gsgsCcjcTrs) {
                    Elements gsgsCcjcTds = gsgsCcjcTr.select("td");
                    AicpubCCheckInfo gsgsCcjcCcjcInfo = new AicpubCCheckInfo();
                    if (gsgsCcjcTds.size() == 5) {
                        String jcssAuthority = gsgsCcjcTds.get(1).text();
                        String gsgsCcjc_type = gsgsCcjcTds.get(2).text();
                        String gsgsCcjc_date = gsgsCcjcTds.get(3).text();
                        String gsgsCcjc_result = gsgsCcjcTds.get(4).text();

                        gsgsCcjcCcjcInfo.setCheckImplAuthority(jcssAuthority);
                        gsgsCcjcCcjcInfo.setType(gsgsCcjc_type);
                        gsgsCcjcCcjcInfo.setDateTime(gsgsCcjc_date);
                        gsgsCcjcCcjcInfo.setResult(gsgsCcjc_result);
                    }

                    gsgsCcjcCcjcInfos.add(gsgsCcjcCcjcInfo);
                }

                if (isDebug) {
                    gsgsCcjcInfo.setHtml(gsgsCcjcTable.toString());
                }
            }
        }

        gsgsCcjcInfo.setCheckInfos(gsgsCcjcCcjcInfos);
        gsgsInfo.setCheckInfo(gsgsCcjcInfo);
        //-----------------工商公示信息-->抽查检查信息 end-----------------------

        //二、企业公示信息
        EntpubInfo qygsInfo = new EntpubInfo();

        //-----------------企业公示信息-->企业年报 start-------------------------

        List<EntpubAnnreportInfo> entpubAnnreportInfos = new ArrayList<>();
        //企业年报> 年报列表 (报送年度、发布日期)
        Object qygsxx_qynbxx_object = resultHtmlMap.get("qygsxx_ndbgxx_infos");
        if (qygsxx_qynbxx_object != null) {
            List<Map<String, Object>> qynbDetailList = (List<Map<String, Object>>)qygsxx_qynbxx_object;
            for (Map<String, Object> qynbDetailMap : qynbDetailList) {
                EntpubAnnreportInfo qygsQynbInfo = new EntpubAnnreportInfo();

                Object qygsxx_qynb_list_a_text_object = qynbDetailMap.get("qygsxx_qynb_list_a_text");
                if (qygsxx_qynb_list_a_text_object != null) {
                    String submitYear = (String)qygsxx_qynb_list_a_text_object;
                    qygsQynbInfo.setSubmitYear(submitYear);
                }

                Object qygsxx_qynb_list_pubdate_object = qynbDetailMap.get("qygsxx_qynb_list_pubdate");
                if (qygsxx_qynb_list_pubdate_object != null) {
                    String pubDate = (String)qygsxx_qynb_list_pubdate_object;
                    qygsQynbInfo.setPubDateTime(pubDate);
                }

                //企业年报详情 > 企业基本信息、企业资产状况信息
                Object qygsxx_qynb_info_1_4_page_object = qynbDetailMap.get("qygsxx_qynb_info_1_4_page");
                EntpubAnnreportBaseInfo entpubAnnreportBaseInfo = new EntpubAnnreportBaseInfo(); //企业基本信息
                if (qygsxx_qynb_info_1_4_page_object != null) {
                    String qygsxx_qynb_info_1_4_page = (String) qygsxx_qynb_info_1_4_page_object;
                    Document qygsxxQynb14Doc = Jsoup.parse(qygsxx_qynb_info_1_4_page);
                    Elements qygsxxQynb14Tables = qygsxxQynb14Doc.select("table");
                    for (Element qygsxxQynb14Table : qygsxxQynb14Tables) {
                        String qygsxxQynbTitle = qygsxxQynb14Table.select("th").get(0).text();

                        if (qygsxxQynbTitle.indexOf("年度报告") > 0) {
                            qygsxxQynbTitle = qygsxxQynb14Table.select("th").get(1).text();
                        }

                        Elements qygsxxQynb14Ths = qygsxxQynb14Table.select("th");
                        Elements qygsxxQynb14Tds = qygsxxQynb14Table.select("td");
                        qygsxxQynb14Ths.remove(0);
                        if (qygsxxQynbTitle.equals("企业基本信息")) { //企业基本信息
                            qygsxxQynb14Ths.remove(0);
                            for (int j = 0; j < qygsxxQynb14Ths.size(); j++) {
                                String qynbQyjbTh = qygsxxQynb14Ths.get(j).text().trim();
                                String qynbQyjbTd = qygsxxQynb14Tds.get(j).text();

                                switch (qynbQyjbTh) {
                                    case "注册号":
                                        entpubAnnreportBaseInfo.setNum(qynbQyjbTd);
                                        break;
                                    case "企业名称":
                                        entpubAnnreportBaseInfo.setName(qynbQyjbTd);
                                        break;
                                    case "企业联系电话":
                                        entpubAnnreportBaseInfo.setTel(qynbQyjbTd);
                                        break;
                                    case "邮政编码":
                                        entpubAnnreportBaseInfo.setZipCode(qynbQyjbTd);
                                        break;
                                    case "企业通信地址":
                                        entpubAnnreportBaseInfo.setAddress(qynbQyjbTd);
                                        break;
                                    case "电子邮箱":
                                        entpubAnnreportBaseInfo.setEmail(qynbQyjbTd);
                                        break;
//                                    case "企业是否有投资信息或购买其他公司股权":
//                                        entpubAnnreportBaseInfo.setHasInvestInfoOrPurchOtherCorpEqu(qynbQyjbTd);
//                                        break;
                                    case "企业经营状态":
                                        entpubAnnreportBaseInfo.setOperatingStatus(qynbQyjbTd);
                                        break;
//                                    case "是否有网站或网店":
//                                        entpubAnnreportBaseInfo.setHasWebsiteOrStore(qynbQyjbTd);
//                                        break;
                                    case "从业人数":
                                        entpubAnnreportBaseInfo.setEmpNum(qynbQyjbTd);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }else if ("企业资产状况信息".equals(qygsxxQynbTitle)) { //企业资产状况信息
                            EntpubAnnreportAssetInfo qygsQynbQyzczkInfo = new EntpubAnnreportAssetInfo();

                            for (int j = 0; j < qygsxxQynb14Ths.size(); j++) {
                                String qygsxxQynb14Th = qygsxxQynb14Ths.get(j).text().trim();
                                String qygsxxQynb14Td = qygsxxQynb14Tds.get(j).text();

                                switch (qygsxxQynb14Th) {
                                    case "资产总额":
                                        qygsQynbQyzczkInfo.setAssetAmount(qygsxxQynb14Td);
                                        break;
                                    case "所有者权益合计":
                                        qygsQynbQyzczkInfo.setTotalEquity(qygsxxQynb14Td);
                                        break;
                                    case "销售总额":
                                        qygsQynbQyzczkInfo.setSalesAmount(qygsxxQynb14Td);
                                        break;
                                    case "营业总收入":
                                        qygsQynbQyzczkInfo.setSalesAmount(qygsxxQynb14Td);
                                        break;
                                    case "利润总额":
                                        qygsQynbQyzczkInfo.setProfitAmount(qygsxxQynb14Td);
                                        break;
                                    case "营业总收入中主营业务收入":
                                        qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qygsxxQynb14Td);
                                        break;
                                    case "主营业务收入":
                                        qygsQynbQyzczkInfo.setPriBusiIncomeInSalesAmount(qygsxxQynb14Td);
                                        break;
                                    case "净利润":
                                        qygsQynbQyzczkInfo.setNetProfit(qygsxxQynb14Td);
                                        break;
                                    case "纳税总额":
                                        qygsQynbQyzczkInfo.setTaxesAmount(qygsxxQynb14Td);
                                        break;
                                    case "负债总额":
                                        qygsQynbQyzczkInfo.setLiabilityAmount(qygsxxQynb14Td);
                                        break;
                                    default:
                                        break;
                                }

                            }
                            qygsQynbInfo.setAssetInfo(qygsQynbQyzczkInfo);

                            //生产经营情况
                        } else if ("生产经营情况".equals(qygsxxQynbTitle)) {
                            List<EntpubAnnreportManageInfo> manageInfos = new ArrayList<>();
                            EntpubAnnreportManageInfo scjyqkInfo = new EntpubAnnreportManageInfo();

                            for (int j = 0; j < qygsxxQynb14Ths.size(); j++) {
                                String qygsxxQynb14Th = qygsxxQynb14Ths.get(j).text().trim();
                                String qygsxxQynb14Td = qygsxxQynb14Tds.get(j).text();

                                switch (qygsxxQynb14Th){
                                    case "主营业务收入":
                                        scjyqkInfo.setSaleSum(qygsxxQynb14Td);
                                        break;
                                    case "纳税总额":
                                        scjyqkInfo.setSalarySum(qygsxxQynb14Td);
                                        break;
                                    case "净利润":
                                        scjyqkInfo.setNetProfit(qygsxxQynb14Td);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }

                    }
                }

                //1、获取企业年报详情 -> 网站或网店信息
                List<Website> websiteList = null;
                List<EntpubAnnreportWebsiteInfo> websiteInfos = new ArrayList<>();
                Object qynb_info_wzhwd_pages_object = qynbDetailMap.get("qynb_info_wzhwd_pages");
                if (qynb_info_wzhwd_pages_object != null) {
                    List<String> wzhwd_pages = (List<String>) qynb_info_wzhwd_pages_object;
                    for (String wzhwdPage : wzhwd_pages) {
                        String wzhwdPageResult = wzhwdPage.replace(",\"busWebSysTime\":,", ",\"busWebSysTime\":{},");
                        websiteList = gson.fromJson(wzhwdPageResult.toString(), new TypeToken<List<Website>>(){}.getType());
                        if (websiteList != null && !websiteList.isEmpty()) {
                            for (Website website : websiteList) {
                                EntpubAnnreportWebsiteInfo wzhwdInfo = new EntpubAnnreportWebsiteInfo();
                                String busWebType = website.getBusWebType();
                                if ("1".equals(busWebType)) {
                                    wzhwdInfo.setType("网店");
                                }else if ("0".equals(busWebType)) {
                                    wzhwdInfo.setType("网站");
                                }
                                wzhwdInfo.setName(website.getBusWebWebsiteName());
                                wzhwdInfo.setWebsite(website.getBusWebWebsite());
                                websiteInfos.add(wzhwdInfo);
                            }
                        }
                    }
//                    System.out.println("wzhwd_pages: "+wzhwd_pages);
                }
                qygsQynbInfo.setWebsiteInfos(websiteInfos);

                //2、获取企业年报详情 -> 对外投资信息
                List<ExtInvest> extInvestList = null;
                List<EntpubAnnreportExtinvestInfo> extInvestInfos = new ArrayList<>();
                Object qynb_info_dwtz_pages_object = qynbDetailMap.get("qynb_info_dwtz_pages");
                if (qynb_info_dwtz_pages_object != null) {
                    List<String> dwtz_pages = (List<String>) qynb_info_dwtz_pages_object;
                    for (String dwtzPage : dwtz_pages) {
                        extInvestList = gson.fromJson(dwtzPage.toString(), new TypeToken<List<ExtInvest>>(){}.getType());
                        if (extInvestList != null && !extInvestList.isEmpty()) {
                            for (ExtInvest datalist : extInvestList) {
                                EntpubAnnreportExtinvestInfo dwtzInfo = new EntpubAnnreportExtinvestInfo();
                                dwtzInfo.setEnterpriseName(datalist.getInvestInfoEntName());
                                dwtzInfo.setRegNum(datalist.getInvestInfoEntRegNo());
                                extInvestInfos.add(dwtzInfo);
                            }
                        }
                    }
                }
                qygsQynbInfo.setExtInvestInfos(extInvestInfos);

                //3、获取企业年报详情 -> 对外提供保证担保信息
                List<EntpubAnnreportExtguaranteeInfo> extGuaranteeInfos = new ArrayList<>(); //
//                qygsQynbInfo.setExtGuaranteeInfos(extGuaranteeInfos);

                //4、获取企业年报详情 -> 修改记录
                List<ModifyInfo> modifyInfoList = null;
                List<EntpubAnnreportModifyInfo> changeInfos = new ArrayList<>();
                Object qynb_info_updateRecord_pages_object = qynbDetailMap.get("qynb_info_updateRecord_pages");
                if (qynb_info_updateRecord_pages_object != null) {
                    List<String> updateRecord_pages = (List<String>) qynb_info_updateRecord_pages_object;
                    for (String updateRecordPage : updateRecord_pages) {
                        modifyInfoList = gson.fromJson(updateRecordPage.toString(), new TypeToken<List<ModifyInfo>>(){}.getType());
                        if (modifyInfoList != null && !modifyInfoList.isEmpty()) {
                            for (ModifyInfo datalist : modifyInfoList) {
                                EntpubAnnreportModifyInfo updateModifyInfo = new EntpubAnnreportModifyInfo();
                                updateModifyInfo.setItem(datalist.getModItemName());
                                String modContentBefore = datalist.getModContentBefore();
                                if ("1".equals(modContentBefore)) {
                                    updateModifyInfo.setPreContent("网店");
                                }else if ("0".equals(modContentBefore)) {
                                    updateModifyInfo.setPreContent("网站");
                                }else {
                                    updateModifyInfo.setPreContent(datalist.getModContentBefore());
                                }
                                String modContentAfter = datalist.getModContentAfter();
                                if (modContentAfter == null || "".equals(modContentAfter)) {
                                    updateModifyInfo.setPostContent("此项已删除");
                                }else {
                                    updateModifyInfo.setPostContent(datalist.getModContentAfter());
                                }
                                if (datalist.getModDate() != null && !"".equals(datalist.getModDate())) {
                                    String modDate = new SimpleDateFormat("yyyy年MM月dd日").format(datalist.getModDate().getTime());
                                    updateModifyInfo.setDateTime(modDate);
                                }

                                changeInfos.add(updateModifyInfo);
                            }
                        }
                    }
                }
//                System.err.println("===changeInfos====" + changeInfos);
                qygsQynbInfo.setChangeInfos(changeInfos);

                //5、获取企业年报详情 -> 股东及出资信息
                List<StohrInvest> stohrInvestList = null;
                List<EntpubAnnreportStohrinvestInfo> stohrInvestInfos = new ArrayList<>();
                Object qynb_info_gdjcz_pages_object = qynbDetailMap.get("qynb_info_gdjcz_pages");
                if (qynb_info_gdjcz_pages_object != null) {
                    List<String> gdjcz_pages = (List<String>) qynb_info_gdjcz_pages_object;
                    for (String gdjczPage : gdjcz_pages) {
//                        System.out.println("gdjcz_pages: "+gdjcz_pages);
                        String gdjczPageResult = gdjczPage.replace(",\"conInfoPayDate\":,",",\"conInfoPayDate\":{},").replace(",\"conInfoActDate\":,", ",\"conInfoActDate\":{},");
                        stohrInvestList = gson.fromJson(gdjczPageResult.toString(), new TypeToken<List<StohrInvest>>(){}.getType());
                        if (stohrInvestList != null && !stohrInvestList.isEmpty()) {
                            for (StohrInvest datalist : stohrInvestList) {
                                EntpubAnnreportStohrinvestInfo gdjczInfo = new EntpubAnnreportStohrinvestInfo();
                                gdjczInfo.setPaidAmount(datalist.getConInfoPayConAmount());
                                if (datalist.getConInfoPayDate()!= null && !"".equals(datalist.getConInfoPayDate())) {
                                    String conInfoPayDate = new SimpleDateFormat("yyyy年MM月dd日").format(datalist.getConInfoPayDate().getTime());
                                    gdjczInfo.setPaidDateTime(conInfoPayDate);
                                }
                                String[] conInfoInvFormArr = datalist.getConInfoInvForm().split(",");
                                String conInfoInvForm = "";
                                for (String ciif : conInfoInvFormArr) {
                                    if ("1".equals(ciif)) {
                                        conInfoInvForm += "货币";
                                    }
                                }
                                gdjczInfo.setPaidMethod(conInfoInvForm);

                                gdjczInfo.setStockholder(datalist.getConInfoName());
                                gdjczInfo.setSubAmount(datalist.getConInfoActConAmount());
                                if (datalist.getConInfoActDate()!=null && !"".equals(datalist.getConInfoActDate())) {
                                    String conInfoActDate = new SimpleDateFormat("yyyy年MM月dd日").format(datalist.getConInfoActDate().getTime());
                                    gdjczInfo.setSubDateTime(conInfoActDate);
                                }
                                String[] conInfoActFormArr = datalist.getConInfoActForm().split(",");
                                String conInfoActForm = "";
                                for (String ciaf : conInfoActFormArr) {
                                    if ("1".equals(ciaf)) {
                                        conInfoActForm += "货币";
                                    }
                                }
                                gdjczInfo.setSubMethod(conInfoActForm);

                                stohrInvestInfos.add(gdjczInfo);
                            }
                        }
                    }
                }
//                System.err.println("===stohrInvestInfos====" + stohrInvestInfos);
                qygsQynbInfo.setStohrInvestInfos(stohrInvestInfos);

                //6、获取企业年报详情 -> 股权变更信息
                List<EquChange> equChangeList = null;
                List<EntpubAnnreportEquchangeInfo> equChangeInfos = new ArrayList<>();
                Object qynb_info_gqbg_pages_object = qynbDetailMap.get("qynb_info_gqbg_pages");
                if (qynb_info_gqbg_pages_object != null) {
                    List<String> gqbg_pages = (List<String>) qynb_info_gqbg_pages_object;
                    for (String gqbgPage : gqbg_pages) {
                        equChangeList = gson.fromJson(gqbgPage.toString(), new TypeToken<List<EquChange>>(){}.getType());
                        if (equChangeList != null && !equChangeList.isEmpty()) {
                            for (EquChange datalist : equChangeList) {
                                EntpubAnnreportEquchangeInfo gqbgInfo = new EntpubAnnreportEquchangeInfo();
                                gqbgInfo.setStockholder(datalist.getStockHolder());
                                gqbgInfo.setPreOwnershipRatio(datalist.getStockAfterPercent()+"%");
                                gqbgInfo.setPostOwnershipRatio(datalist.getStockBeforePercent()+"%");
                                BusWebSysTime stockChangeDate = datalist.getStockChangeDate();
                                if (stockChangeDate != null && !"".equals(stockChangeDate)) {
                                    String dateTime = new SimpleDateFormat("yyyy年MM月dd日").format(stockChangeDate.getTime());
                                    gqbgInfo.setDateTime(dateTime);
                                }
                                equChangeInfos.add(gqbgInfo);
                            }
                        }
                    }
                }
//                System.err.println("===equChangeInfos==" + equChangeInfos);
                qygsQynbInfo.setEquChangeInfos(equChangeInfos);

                //设置企业年报中-> 企业基本信息的属性值: (企业是否有投资信息或购买其他公司股权)、(是否有网站或网店)
                //企业是否有投资信息或购买其他公司股权
                if (extInvestInfos != null && !extInvestInfos.isEmpty()) {
                    entpubAnnreportBaseInfo.setHasInvestInfoOrPurchOtherCorpEqu("是");
                }else {
                    entpubAnnreportBaseInfo.setHasInvestInfoOrPurchOtherCorpEqu("否");
                }
                //是否有网站或网店
                if (websiteInfos!=null && !websiteInfos.isEmpty()) {
                    entpubAnnreportBaseInfo.setHasWebsiteOrStore("是");
                }else {
                    entpubAnnreportBaseInfo.setHasWebsiteOrStore("否");
                }
                //是否有对外担保信息
                if (extGuaranteeInfos != null && !extGuaranteeInfos.isEmpty()) {
                    entpubAnnreportBaseInfo.setHasExternalSecurity("是");
                } else {
                    entpubAnnreportBaseInfo.setHasExternalSecurity("否");
                }
                //有限责任公司本年度是否发生股东股权转让
                if (equChangeInfos!=null && !equChangeInfos.isEmpty()) {
                    entpubAnnreportBaseInfo.setIsStohrEquTransferred("是");
                } else  {
                    entpubAnnreportBaseInfo.setIsStohrEquTransferred("否");
                }

                qygsQynbInfo.setBaseInfo(entpubAnnreportBaseInfo);
                entpubAnnreportInfos.add(qygsQynbInfo);
            }
        }
        qygsInfo.setAnnReports(entpubAnnreportInfos);
        //-----------------企业公示信息-->企业年报 end----------------------------


        //-----------------企业公示信息-->股东及出资信息 start----------------------
        EntpubStohrinvestInfo qygsGdjczInfo = new EntpubStohrinvestInfo();
            //股东及出资信息-->股东及出资信息
        List<EntpubStohrInvestInfo> gdjczList = new ArrayList<>();
        List<EntpubSStohrinvestInfo> qygsGdjczGdjczInfos = new ArrayList<EntpubSStohrinvestInfo>();
        Object qygs_gdjcz_gdjcz_pages_object = resultHtmlMap.get("qygsxx_gdjczxx_gdjczxx");
        if (qygs_gdjcz_gdjcz_pages_object != null) {
            List<String> qygs_gdjcz_gdjcz_pages = (List<String>) qygs_gdjcz_gdjcz_pages_object;
            for (String qygs_gdjcz_gdjczPage : qygs_gdjcz_gdjcz_pages) {
                gdjczList = gson.fromJson(qygs_gdjcz_gdjczPage.toString(), new TypeToken<List<EntpubStohrInvestInfo>>(){}.getType());
                if (gdjczList != null && !gdjczList.isEmpty()) {
                    for (EntpubStohrInvestInfo datalist : gdjczList) {
                        EntpubSStohrinvestInfo gdjczInfo = new EntpubSStohrinvestInfo();
                        gdjczInfo.setStockholder(datalist.getFundInvestor());
                        gdjczInfo.setSubAmount(datalist.getPayAmountCount());
                        gdjczInfo.setPaidAmount(datalist.getActAmountCount());
                        List<PubFunded> pubFundedList = datalist.getPubFundedList();
                        if (pubFundedList!=null && !pubFundedList.isEmpty()) {
                            //认缴明细
                            List<EntpubSStohrinvestInfo.Detail> rjDetails = new ArrayList<>();
                            if (pubFundedList.get(0) != null) {
                                EntpubSStohrinvestInfo.Detail rjDetail = gdjczInfo.new Detail();

                                String rjczMethod = pubFundedList.get(0).getFundForm();
                                if (rjczMethod=="1" || "1".equals(rjczMethod)) {
                                    rjDetail.method = "货币";

                                }else {
                                    rjDetail.method = rjczMethod;
                                }
                                String rjczAmount =  pubFundedList.get(0).getFundAmount();
                                rjDetail.amount = rjczAmount + "万元";
                                BusWebSysTime fundDate = pubFundedList.get(0).getFundDate();
                                if (fundDate != null && !"".equals(fundDate)) {
                                    String rjczDate = new SimpleDateFormat("yyyy年MM月dd日").format(fundDate.getTime());
                                    rjDetail.dateTime = rjczDate;
                                }
                                rjDetails.add(rjDetail);
                            }
                            gdjczInfo.setSubDetails(rjDetails);

                            //实缴明细
                            List<EntpubSStohrinvestInfo.Detail> sjDetails = new ArrayList<>();
                            if (pubFundedList.get(1) != null) {
                                EntpubSStohrinvestInfo.Detail sjDetail = gdjczInfo.new Detail();

                                String sjczMethod = pubFundedList.get(1).getFundForm();
                                if (sjczMethod=="1" || "1".equals(sjczMethod)) {
                                    sjDetail.method = "货币";

                                }else {
                                    sjDetail.method = sjczMethod;
                                }
                                String sjczAmount =  pubFundedList.get(1).getFundAmount();
                                sjDetail.amount = sjczAmount + "万元";
                                BusWebSysTime fundDate = pubFundedList.get(1).getFundDate();
                                if (fundDate != null && !"".equals(fundDate)) {
                                    String sjczDate = new SimpleDateFormat("yyyy年MM月dd日").format(fundDate.getTime());
                                    sjDetail.dateTime = sjczDate;
                                }
                                sjDetails.add(sjDetail);
                            }
                            gdjczInfo.setPaidDetails(sjDetails);
                        }
                        qygsGdjczGdjczInfos.add(gdjczInfo);
                    }
                }
            }
            System.err.println("===qygsGdjczGdjczInfos==" + qygsGdjczGdjczInfos);
            qygsGdjczInfo.setStohrInvestInfos(qygsGdjczGdjczInfos);
        }

        //股东及出资信息-->变更信息
        List<ModifyInfo> gdjczBgList = null;
        List<EntpubStohrinvestChangeInfo> qygsGdjczBgInfos = new ArrayList<>();
        Object qygs_gdjcz_bg_pages_object = resultHtmlMap.get("qygsxx_gdjczxx_bgxx");
        if (qygs_gdjcz_bg_pages_object != null) {
            List<String> qygs_gdjcz_bg_pages = (List<String>) qygs_gdjcz_bg_pages_object;
            for (String qygs_gdjcz_bgPage : qygs_gdjcz_bg_pages) {
                gdjczBgList = gson.fromJson(qygs_gdjcz_bgPage.toString(), new TypeToken<List<ModifyInfo>>(){}.getType());
                if (gdjczBgList != null && !gdjczBgList.isEmpty()) {
                    for (ModifyInfo datalist : gdjczBgList) {
                        EntpubStohrinvestChangeInfo gdjczBgInfo = new EntpubStohrinvestChangeInfo();
                        gdjczBgInfo.setItem(datalist.getModItemName());
                        BusWebSysTime modidate = datalist.getModDate();
                        if (modidate!=null && !"".equals(modidate)) {
                            String dateTime = new SimpleDateFormat("yyyy年MM月dd日").format(modidate.getTime());
                            gdjczBgInfo.setDateTime(dateTime);
                        }
                        gdjczBgInfo.setPreContent(datalist.getModContentBefore());
                        gdjczBgInfo.setPostContent(datalist.getModContentAfter());

                        qygsGdjczBgInfos.add(gdjczBgInfo);
                    }
                }
            }
            System.err.println("===qygsGdjczBgInfos==" + qygsGdjczBgInfos);
            qygsGdjczInfo.setChangeInfos(qygsGdjczBgInfos);
        }


        qygsInfo.setStohrInvestInfo(qygsGdjczInfo);
        //-----------------企业公示信息-->股东及出资信息 end------------------------


        //-----------------企业公示信息-->股权变更信息 start------------------------
        List<EntpubEquchange> entpubEquChangeList = null;
        EntpubEquchangeInfo entpubEquchangeInfo = new EntpubEquchangeInfo();
        List<EntpubEEquchangeInfo> entpubEquChangeInfos = new ArrayList<>();
        Object qygs_gqbg_pages_object = resultHtmlMap.get("qygsxx_gdbgxx");
        if (qygs_gqbg_pages_object != null) {
            List<String> qygs_gqbg_pages = (List<String>) qygs_gqbg_pages_object;
            for (String qygs_gqbgPage : qygs_gqbg_pages) {
                entpubEquChangeList = gson.fromJson(qygs_gqbgPage.toString(), new TypeToken<List<EntpubEquchange>>(){}.getType());
                if (entpubEquChangeList != null && !entpubEquChangeList.isEmpty()) {
                    for (EntpubEquchange datalist : entpubEquChangeList) {
                        EntpubEEquchangeInfo gqbgInfo = new EntpubEEquchangeInfo();
                        gqbgInfo.setStockholder(datalist.getStockBeforeInv());
                        gqbgInfo.setPreOwnershipRatio(datalist.getStockBeforeTransampr() + "%");
                        gqbgInfo.setPostOwnershipRatio(datalist.getStockAfterTransampr() + "%");

                        BusWebSysTime stockChangeDate = datalist.getStockChangeDate();
                        if (stockChangeDate != null && !"".equals(stockChangeDate)) {
                            String dateTime = new SimpleDateFormat("yyyy年MM月dd日").format(stockChangeDate.getTime());
                            gqbgInfo.setDateTime(dateTime);
                        }
                        entpubEquChangeInfos.add(gqbgInfo);
                    }
                }
            }
            entpubEquchangeInfo.setEquChangeInfos(entpubEquChangeInfos);
        }

//        System.err.println("==entpubEquchangeInfo==" + entpubEquchangeInfo);
        qygsInfo.setEquChangeInfo(entpubEquchangeInfo);
        //-----------------企业公示信息-->股权变更信息 end------------------------

        //-----------------企业公示信息-->行政许可信息 start-----------------------
        EntpubAdmlicInfo qygsXzxkInfo = new EntpubAdmlicInfo();
        List<EntpubAAdmlicInfo> qygsXzxkXzxkInfos = new ArrayList<EntpubAAdmlicInfo>();
        Object qygsxx_xzxkxx_object = resultHtmlMap.get("qygsxx_xzxkxx");

        qygsXzxkInfo.setAdmlicInfos(qygsXzxkXzxkInfos);

//        qygsInfo.setAdmLicInfo(qygsXzxkInfo);
        //-----------------企业公示信息-->行政许可信息 end-----------------------


        //-----------------企业公示信息-->知识产权出质登记信息 start-----------------------
        EntpubIntellectualproregInfo qygsZscqczdjInfo = new EntpubIntellectualproregInfo();
        List<EntpubIIntellectualproregInfo> qygsZscqczdjZscqczdjInfos = new ArrayList<EntpubIIntellectualproregInfo>();
        Object qygsxx_zscqczdjxx_object = resultHtmlMap.get("qygsxx_zscqczdjxx");

        qygsZscqczdjInfo.setIntellectualProRegInfos(qygsZscqczdjZscqczdjInfos);

//        qygsInfo.setIntellectualProRegInfo(qygsZscqczdjInfo);
        //-----------------企业公示信息-->知识产权出质登记信息 end-----------------------

        //-----------------企业公示信息-->行政处罚信息 start-----------------------
        EntpubAdmpunishInfo qygsXzcfInfo = new EntpubAdmpunishInfo();
        List<EntpubAAdmpunishInfo> qygsXzcfXzcfInfos = new ArrayList<EntpubAAdmpunishInfo>();
        Object qygsxx_xzcfxx_object = resultHtmlMap.get("qygsxx_xzcfxx");

        qygsXzcfInfo.setAdmPunishInfos(qygsXzcfXzcfInfos);

//        qygsInfo.setAdmPunishInfo(qygsXzcfInfo);
        //-----------------企业公示信息-->行政处罚信息 end-----------------------

        //三、其他部门公示信息
        OthrdeptpubInfo qtbmgsInfo = new OthrdeptpubInfo();

        //-----------------其他部门公示信息-->行政许可信息 start-----------------------
        OthrdeptpubAdmlicInfo qtbmgsXzxkInfo = new OthrdeptpubAdmlicInfo();
        List<OthrdeptpubAAdmlicInfo> qtbmgsXzxkXzxkInfos = new ArrayList<OthrdeptpubAAdmlicInfo>();
        Object qtbmgsxx_xzxkxx_object = resultHtmlMap.get("otherdept_xzxkjbgxx");
        if (qtbmgsxx_xzxkxx_object != null) {
            String qtbmgsxx_xzxkxx_page = (String) qtbmgsxx_xzxkxx_object;
            Document qtbmgsxxXzxkxxDoc = Jsoup.parse(qtbmgsxx_xzxkxx_page);
            Elements qtbmgsxxXzxkxxTables = qtbmgsxxXzxkxxDoc.select("table");
            if (qtbmgsxxXzxkxxTables != null && !qtbmgsxxXzxkxxTables.isEmpty()) {
                Element qtbmgsxxXzxkxxTable = qtbmgsxxXzxkxxTables.get(0);
                Elements qtbmgsxxXzxkxxTrs = qtbmgsxxXzxkxxTable.select("tr");
                qtbmgsxxXzxkxxTrs.remove(0);
                qtbmgsxxXzxkxxTrs.remove(0);
                for (Element qtbmgsxxXzxkxxTr : qtbmgsxxXzxkxxTrs) {
                    Elements qtbmgsxxXzxkxxTds = qtbmgsxxXzxkxxTr.select("td");
                    if (qtbmgsxxXzxkxxTds.size() == 9) {
                        String xkwjNum = qtbmgsxxXzxkxxTds.get(1).text();
                        String xkwjName = qtbmgsxxXzxkxxTds.get(2).text();
                        String xzxkstartDate = qtbmgsxxXzxkxxTds.get(3).text();
                        String xzxkendDate = qtbmgsxxXzxkxxTds.get(4).text();
                        String xkAuthority = qtbmgsxxXzxkxxTds.get(5).text();
                        String xkContent = qtbmgsxxXzxkxxTds.get(6).text();
                        String status = qtbmgsxxXzxkxxTds.get(7).text();
                        String detail = qtbmgsxxXzxkxxTds.get(8).text();

                        OthrdeptpubAAdmlicInfo qtbmgsXzxkXzxkInfo = new OthrdeptpubAAdmlicInfo();
                        qtbmgsXzxkXzxkInfo.setLicenceNum(xkwjNum);
                        qtbmgsXzxkXzxkInfo.setLicenceName(xkwjName);
                        qtbmgsXzxkXzxkInfo.setStartDateTime(xzxkstartDate);
                        qtbmgsXzxkXzxkInfo.setEndDateTime(xzxkendDate);
                        qtbmgsXzxkXzxkInfo.setDeciAuthority(xkAuthority);
                        qtbmgsXzxkXzxkInfo.setContent(xkContent);
                        qtbmgsXzxkXzxkInfo.setStatus(status);
                        qtbmgsXzxkXzxkInfo.setDetail(detail);
                        qtbmgsXzxkXzxkInfos.add(qtbmgsXzxkXzxkInfo);
                    }
                }

                if (isDebug) {
                    qtbmgsXzxkInfo.setHtml(qtbmgsxxXzxkxxTable.toString());
                }
            }
        }
        qtbmgsXzxkInfo.setAdmLicInfos(qtbmgsXzxkXzxkInfos);

        qtbmgsInfo.setAdmLicInfo(qtbmgsXzxkInfo);
        //-----------------其他部门公示信息-->行政许可信息 end-----------------------

        //-----------------其他部门公示信息-->行政处罚信息 start-----------------------
        OthrdeptpubAdmpunishInfo qtbmgsXzcfInfo = new OthrdeptpubAdmpunishInfo();
        List<OthrdeptpubAAdmpunishInfo> qtbmgsXzcfXzcfInfos = new ArrayList<OthrdeptpubAAdmpunishInfo>();
        Object qtbmgsxx_xzcfxx_object = resultHtmlMap.get("otherdept_xzcfxx");
        if (qtbmgsxx_xzcfxx_object != null) {
            String qtbmgsxx_xzcfxx_page = (String) qtbmgsxx_xzcfxx_object;
            Document qtbmgsxxXzcfxxDoc = Jsoup.parse(qtbmgsxx_xzcfxx_page);
            Elements qtbmgsxxXzcfxxTables = qtbmgsxxXzcfxxDoc.select("table");
            if (qtbmgsxxXzcfxxTables != null && !qtbmgsxxXzcfxxTables.isEmpty()) {
                Element qtbmgsxxXzcfxxTable = qtbmgsxxXzcfxxTables.get(0);
                Elements qtbmgsxxXzcfxxTrs = qtbmgsxxXzcfxxTable.select("tr");
                qtbmgsxxXzcfxxTrs.remove(0);
                qtbmgsxxXzcfxxTrs.remove(0);
                for (Element qtbmgsxxXzcfxxTr : qtbmgsxxXzcfxxTrs) {
                    Elements qtbmgsxxXzcfxxTds = qtbmgsxxXzcfxxTr.select("td");
                    if (qtbmgsxxXzcfxxTds.size() == 6) {
                        String xzcfjdsNum = qtbmgsxxXzcfxxTds.get(1).text();
                        String wfxwType = qtbmgsxxXzcfxxTds.get(2).text();
                        String xzcfContent = qtbmgsxxXzcfxxTds.get(3).text();
                        String zcxzcfjdjgName = qtbmgsxxXzcfxxTds.get(4).text();
                        String zcxzcfjdDate = qtbmgsxxXzcfxxTds.get(5).text();

                        OthrdeptpubAAdmpunishInfo qtbmgsXzcfXzcfInfo = new OthrdeptpubAAdmpunishInfo();
                        qtbmgsXzcfXzcfInfo.setPunishRepNum(xzcfjdsNum);
                        qtbmgsXzcfXzcfInfo.setIllegalActType(wfxwType);
                        qtbmgsXzcfXzcfInfo.setPunishContent(xzcfContent);
                        qtbmgsXzcfXzcfInfo.setDeciAuthority(zcxzcfjdjgName);
                        qtbmgsXzcfXzcfInfo.setDeciDateTime(zcxzcfjdDate);
                        qtbmgsXzcfXzcfInfos.add(qtbmgsXzcfXzcfInfo);
                    }
                }

                if (isDebug) {
                    qtbmgsXzcfInfo.setHtml(qtbmgsxxXzcfxxTable.toString());
                }
            }
        }
        qtbmgsXzcfInfo.setAdmPunishInfos(qtbmgsXzcfXzcfInfos);

        qtbmgsInfo.setAdmPunishInfo(qtbmgsXzcfInfo);
        //-----------------其他部门公示信息-->行政处罚信息 end-----------------------

        //四、司法协助公示信息
        JudasspubInfo sfxzgsInfo = new JudasspubInfo();

        //-----------------司法协助公示信息-->股权冻结信息 start-----------------------
        //司法股权冻结信息
        JudasspubEqufreezeInfo sfxzgsSfgqdjInfo = new JudasspubEqufreezeInfo();
        List<JudasspubEEqufreezeInfo> sfxzgsGqdjGqdjInfos = new ArrayList<JudasspubEEqufreezeInfo>();
        Object sfxzgsxx_gqdjxx_object = resultHtmlMap.get("sfxzgsxx_gqdjxx");
        if (sfxzgsxx_gqdjxx_object != null) {
            String sfxzgsxx_gqdjxx_page = (String) sfxzgsxx_gqdjxx_object;
            Document sfxzgsxxGqdjxxDoc = Jsoup.parse(sfxzgsxx_gqdjxx_page);
            Elements sfxzgsxxGqdjxxTables = sfxzgsxxGqdjxxDoc.select("table");
            if (sfxzgsxxGqdjxxTables != null && !sfxzgsxxGqdjxxTables.isEmpty()) {
                Element sfxzgsxxGqdjxxTable = sfxzgsxxGqdjxxTables.get(0);
                Elements sfxzgsxxGqdjxxTrs = sfxzgsxxGqdjxxTable.select("tr");
                sfxzgsxxGqdjxxTrs.remove(0);
                sfxzgsxxGqdjxxTrs.remove(0);
                for (Element qtbmgsxxXzcfxxTr : sfxzgsxxGqdjxxTrs) {
                    Elements qtbmgsxxXzcfxxTds = qtbmgsxxXzcfxxTr.select("td");
                    if (qtbmgsxxXzcfxxTds.size() == 7) {
                        String bzxPerson = qtbmgsxxXzcfxxTds.get(1).text();
                        String gqAmount = qtbmgsxxXzcfxxTds.get(2).text();
                        String exeCourt = qtbmgsxxXzcfxxTds.get(3).text();
                        String xzgstzsNum = qtbmgsxxXzcfxxTds.get(4).text();
                        String status = qtbmgsxxXzcfxxTds.get(5).text();
                        String detail = qtbmgsxxXzcfxxTds.get(6).text();

                        JudasspubEEqufreezeInfo sfxzgsGqdjGqdjInfo = new JudasspubEEqufreezeInfo();
                        sfxzgsGqdjGqdjInfo.setExecutedPerson(bzxPerson);
                        sfxzgsGqdjGqdjInfo.setEquAmount(gqAmount);
                        sfxzgsGqdjGqdjInfo.setExeCourt(exeCourt);
                        sfxzgsGqdjGqdjInfo.setAssistPubNoticeNum(xzgstzsNum);
                        sfxzgsGqdjGqdjInfo.setStatus(status);
                        sfxzgsGqdjGqdjInfo.setDetail(detail);
                        sfxzgsGqdjGqdjInfos.add(sfxzgsGqdjGqdjInfo);
                    }
                }

                if (isDebug) {
                    qtbmgsXzcfInfo.setHtml(sfxzgsxxGqdjxxTable.toString());
                }
            }
        }
        sfxzgsInfo.setEquFreezeInfo(sfxzgsSfgqdjInfo);


        //-----------------司法协助公示信息-->股权冻结信息 end-------------------------


        //-----------------司法协助公示信息-->股东强制变更信息 start-----------------------
        //司法股东强制变更登记信息
        JudasspubStohrchangeInfo sfxzgsGdqzbgInfo = new JudasspubStohrchangeInfo();
        List<JudasspubSStohrchangeInfo> sfgdqzbgdjInfos = new ArrayList<JudasspubSStohrchangeInfo>();
        Object sfxzgsxx_sfgdqzbgdjxx_object = resultHtmlMap.get("sfxzgsxx_gqdjxx");
        if (sfxzgsxx_sfgdqzbgdjxx_object != null) {
            String sfxzgsxx_sfgdqzbgdjxx_page = (String) sfxzgsxx_sfgdqzbgdjxx_object;
            Document sfxzgsxxSfgdqzbgdjxxDoc = Jsoup.parse(sfxzgsxx_sfgdqzbgdjxx_page);
            Elements sfxzgsxxSfgdqzbgdjxxTables = sfxzgsxxSfgdqzbgdjxxDoc.select("table");
            if (sfxzgsxxSfgdqzbgdjxxTables != null && !sfxzgsxxSfgdqzbgdjxxTables.isEmpty()) {
                Element sfxzgsxxSfgdqzbgdjxxTable = sfxzgsxxSfgdqzbgdjxxTables.get(0);
                Elements sfxzgsxxSfgdqzbgdjxxTrs = sfxzgsxxSfgdqzbgdjxxTable.select("tr");
                sfxzgsxxSfgdqzbgdjxxTrs.remove(0);
                sfxzgsxxSfgdqzbgdjxxTrs.remove(0);
                for (Element qtbmgsxxXzcfxxTr : sfxzgsxxSfgdqzbgdjxxTrs) {
                    Elements qtbmgsxxXzcfxxTds = qtbmgsxxXzcfxxTr.select("td");
                    if (qtbmgsxxXzcfxxTds.size() == 6) {
                        String executedPerson = qtbmgsxxXzcfxxTds.get(1).text();
                        String equAmount = qtbmgsxxXzcfxxTds.get(2).text();
                        String assignee = qtbmgsxxXzcfxxTds.get(3).text();
                        String exeCourt = qtbmgsxxXzcfxxTds.get(4).text();

                        JudasspubSStohrchangeInfo sfgdqzbgdjInfo = new JudasspubSStohrchangeInfo();
                        sfgdqzbgdjInfo.setExecutedPerson(executedPerson);
                        sfgdqzbgdjInfo.setEquAmount(equAmount);
                        sfgdqzbgdjInfo.setAssignee(assignee);
                        sfgdqzbgdjInfo.setExeCourt(exeCourt);
                        sfgdqzbgdjInfos.add(sfgdqzbgdjInfo);
                    }
                }

                if (isDebug) {
                    qtbmgsXzcfInfo.setHtml(sfxzgsxxSfgdqzbgdjxxTable.toString());
                }
            }
        }
        sfxzgsInfo.setStohrChangeInfo(sfxzgsGdqzbgInfo);
        //-----------------司法协助公示信息-->股东强制变更信息 end-------------------------

        gsxtFeedJson.setAicPubInfo(gsgsInfo);
        gsxtFeedJson.setEntPubInfo(qygsInfo);
        gsxtFeedJson.setOthrDeptPubInfo(qtbmgsInfo);
        gsxtFeedJson.setJudAssPubInfo(sfxzgsInfo);
        return gsxtFeedJson;
    }

    public AicpubRegChangeInfo getGsgsBgInfo (Element gsgsDjBgtr) {
        AicpubRegChangeInfo gsgsBgInfo = new AicpubRegChangeInfo();

        Elements gsgsDjBgTds = gsgsDjBgtr.select("td");
        if (gsgsDjBgTds.size() == 4) {
            String bgItem = gsgsDjBgTds.get(0).text();
            String preContent = null;
            Element preContentElement = gsgsDjBgTds.get(1);
            Elements preContentSpans = preContentElement.select("span");
            if (preContentSpans != null && !preContentSpans.isEmpty()) {
                String preContentSpan = preContentSpans.last().text();
                preContent = preContentSpan==null?"":preContentSpan.substring(0, preContentSpan.length()-"收起更多".length());
            } else {
                preContent = gsgsDjBgTds.get(1).text();
            }
            String postContent = null;
            Element postContentElement = gsgsDjBgTds.get(2);
            Elements postContentSpans = postContentElement.select("span");
            if (postContentSpans != null && !postContentSpans.isEmpty()) {
                String postContentSpan = postContentSpans.last().text();
                postContent = postContentSpan==null?"":postContentSpan.substring(0, postContentSpan.length()-"收起更多".length());
            } else {
                postContent = gsgsDjBgTds.get(2).text();
            }
            String dateTime = gsgsDjBgTds.get(3).text();

            gsgsBgInfo.setItem(bgItem);
            gsgsBgInfo.setPreContent(preContent);
            gsgsBgInfo.setPostContent(postContent);
            gsgsBgInfo.setDateTime(dateTime);
        }
        return gsgsBgInfo;
    }

    public AicpubRegStohrInfo getGsgsGdInfo(Element gdxxTr) {
        AicpubRegStohrInfo aicpubRegStohrInfo = new AicpubRegStohrInfo();
        Elements gdxxTds = gdxxTr.select("td");
        if(gdxxTds.size() == 5) {
            String type = gdxxTds.get(0).text();
            String name = gdxxTds.get(1).text();
            String idType = gdxxTds.get(2).text();
            String idNum = gdxxTds.get(3).text();

            aicpubRegStohrInfo.setType(type);
            aicpubRegStohrInfo.setName(name);
            aicpubRegStohrInfo.setIdType(idType);
            aicpubRegStohrInfo.setIdNum(idNum);
        }
        return aicpubRegStohrInfo;
    }

}
