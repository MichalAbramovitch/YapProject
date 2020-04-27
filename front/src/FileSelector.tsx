import * as React from "react";

export class FileSelector extends React.Component<{}, {}>
{
    constructor(props: any)
    {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(selectorFiles: any)
    {
        console.log(selectorFiles.type);
    }

    render ()
    {
        return <div>
            <input type="file" onChange={ (e) => this.handleChange(e.target.files) } />
        </div>;
    }
}
export default FileSelector;
