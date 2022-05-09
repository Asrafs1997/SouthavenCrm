package com.thisit.southavencrm.webClientHandler;




import com.thisit.southavencrm.common.Constants;
import com.thisit.southavencrm.dashboard.model.GetprofileResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetprofileAPI {
    @GET(Constants.GETPROFILE)
    Call<GetprofileResponseModel> getprofile(@Query("RequestData") String requsetDate, @Header("Authorization") String auth);
}
