function score = abcNoisyCompareAndScore( UNUSED_targetFileName, cellInfoArray, noiseVariance )
%abcNoisyCompareAndScore
    %originalImage = imread( targetFileName );

    if exist( 'abcGenerateImage', 'file' ) ~= 2
        addpath( '../Matlab/abcCellGenerator/' );
        %addpath( '../Matlab/fminsearchbnd/' );
    end
    cellInfoArray = cell2mat( cellInfoArray );
    %disp( cellInfoArray );

    %score = 1.2;
    targetCanvas = abcTargetCanvas();
    canvas = abcCreateCanvas( cellInfoArray, noiseVariance );
    params = abcDefaultParamsHack();
    score = abcLikelihood( targetCanvas, canvas, params.likelihoodSigma );

end

function params = abcDefaultParamsHack()
    %FIXME remove thos hack
    params = struct();
    params.totalNumberOfCells = 1;
    params.randomiseAlpha = false;
    params.randomiseMajorVsMinor = false;
    params.randomiseNucleusRadius = false;
    params.canvasSize = [ 200 200 ];
    params.likelihoodSigma = 5;
    params = abcParams( params );
end

function score = abcLikelihood( targetCanvas, canvas, sigma )
    tmp1 = targetCanvas - canvas;
    tmp1 = sum( sum( tmp1 .* tmp1 ) ) / ( 2 * sigma * sigma );
    %tmp1
    %imshow( canvas );
    score = exp( - tmp1 );
    %score
end

function canvas = abcTargetCanvas()
    params = abcDefaultParamsHack();
    %stick a cell bang in the middle for now
    cellInfoArray = [ params.canvasSize( 1 ) / 2.0, params.canvasSize( 2 ) / 2.0, 20 ];
    canvas = abcCreateCanvas( cellInfoArray, 0 );
end

function canvas = abcCreateCanvas( cellInfoArray, noiseVariance )
    params = abcDefaultParamsHack();
    canvas = abcEmptyCanvas( params.canvasSize,  true );
    mask   = abcEmptyCanvas( params.canvasSize, false );

    for i = 1:size(cellInfoArray, 1 )
        x = cellInfoArray( i, 1 );
        y = cellInfoArray( i, 2 );
        r = cellInfoArray( i, 3 );

        params.nucleusX = x;
        params.nucleusY = y;

        params.x = x;
        params.y = y;

        params.radius = r;

        allParams = abcParams( params );

        [ thisCellCanvas, thisCellMask, ~, ~ ]  = abcDrawCell( allParams, allParams.canvasSize );

        mask( thisCellMask == 1 ) = 1;
        canvas = thisCellCanvas .* canvas;
    end

    if noiseVariance > 0
        canvas = imnoise( canvas ,'gaussian',0 , noiseVariance );
    end
end

