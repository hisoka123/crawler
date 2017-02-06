package com.crawler.gsxt.domain.json.chongqing;


import java.util.ArrayList;
import java.util.List;

public class AnnulReport {

    private AnnulBase base;
    private List<WebSite> webSites = new ArrayList<>();
    private List<AnnulStock> stocks = new ArrayList<>();
    private List<MNGsentinv> mNGsentinv = new ArrayList<>();
    private Mean means ;
    private List<Modify> modifies = new ArrayList<>();
    private MNFzentproinfo mNFzentproinfo;
    private List<Ngstzentinfo> ngstzentinfos;

    public AnnulBase getBase() {
        return base;
    }

    public void setBase(AnnulBase base) {
        this.base = base;
    }

    public List<WebSite> getWebSites() {
        return webSites;
    }

    public void setWebSites(List<WebSite> webSites) {
        this.webSites = webSites;
    }

    public List<AnnulStock> getStocks() {
        return stocks;
    }

    public void setStocks(List<AnnulStock> stocks) {
        this.stocks = stocks;
    }

    public List<MNGsentinv> getmNGsentinv() {
        return mNGsentinv;
    }

    public void setmNGsentinv(List<MNGsentinv> mNGsentinv) {
        this.mNGsentinv = mNGsentinv;
    }

    public Mean getMeans() {
        return means;
    }

    public void setMeans(Mean means) {
        this.means = means;
    }

    public List<Modify> getModifies() {
        return modifies;
    }

    public void setModifies(List<Modify> modifies) {
        this.modifies = modifies;
    }

    public MNFzentproinfo getmNFzentproinfo() {
        return mNFzentproinfo;
    }

    public void setmNFzentproinfo(MNFzentproinfo mNFzentproinfo) {
        this.mNFzentproinfo = mNFzentproinfo;
    }

	public List<Ngstzentinfo> getNgstzentinfos() {
		return ngstzentinfos;
	}

	public void setNgstzentinfos(List<Ngstzentinfo> ngstzentinfos) {
		this.ngstzentinfos = ngstzentinfos;
	}
}
